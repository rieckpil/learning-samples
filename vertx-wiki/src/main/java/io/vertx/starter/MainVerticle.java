package io.vertx.starter;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

public class MainVerticle extends AbstractVerticle {

	private static final String SQL_CREATE_PAGES_TABLE = "create table if not exists Pages (Id integer identity primary key, Name varchar(255) unique, Content clob)";
	private static final String SQL_GET_PAGE = "select Id, Content from Pages where Name = ?";
	private static final String SQL_CREATE_PAGE = "insert into Pages values (NULL, ?, ?)";
	private static final String SQL_SAVE_PAGE = "update Pages set Content = ? where Id = ?";
	private static final String SQL_ALL_PAGES = "select Name from Pages";
	private static final String SQL_DELETE_PAGE = "delete from Pages where Id = ?";

	private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

	private JDBCClient dbClient;
	private FreeMarkerTemplateEngine templateEngine;

	@Override
	public void start(Future<Void> startFuture) {
		Future<Void> steps = prepareDatabase().compose(v -> startHttpServer());
		steps.setHandler(startFuture.completer());
	}

	private Future<Void> prepareDatabase() {
		Future<Void> future = Future.future();

		dbClient = JDBCClient.createShared(vertx, new JsonObject().put("url", "jdbc:hsqldb:file:db/wiki")
				.put("driver_class", "org.hsqldb.jdbcDriver").put("max_pool_size", 30));

		dbClient.getConnection(ar -> {
			if (ar.failed()) {
				LOGGER.error("Could not open a database connection", ar.cause());
				future.fail(ar.cause());
			} else {
				SQLConnection connection = ar.result();
				connection.execute(SQL_CREATE_PAGES_TABLE, create -> {
					JsonArray params = new JsonArray();
					params.add("Hello World!").add("##Hello123");
					connection.updateWithParams(SQL_CREATE_PAGE, params, created -> {
						connection.close();
						if (create.failed()) {
							LOGGER.error("Database preparation error", create.cause());
							future.fail(create.cause());
						} else {
							future.complete();
						}
					});
				});
			}
		});
		return future;

	}

	private Future<Void> startHttpServer() {
		Future<Void> future = Future.future();
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);
		router.get("/").handler(this::indexHandler);
		router.post().handler(BodyHandler.create());

		templateEngine = FreeMarkerTemplateEngine.create(vertx);

		server.requestHandler(router).listen(8080, ar -> {
			if (ar.succeeded()) {
				LOGGER.info("HTTP server running on port 8080");
				future.complete();
			} else {
				LOGGER.error("Could not start a HTTP server", ar.cause());
				future.fail(ar.cause());
			}
		});
		return future;
	}

	private void indexHandler(RoutingContext context) {

		dbClient.getConnection(car -> {
			if (car.succeeded()) {
				SQLConnection connection = car.result();
				connection.query(SQL_ALL_PAGES, res -> {
					connection.close();

					if (res.succeeded()) {
						List<String> pages = res.result().getResults().stream().map(json -> json.getString(0)).sorted()
								.collect(Collectors.toList());

						context.put("title", "Wiki home");
						context.put("pages", pages);
						templateEngine.render(context.data(), "templates/index.ftl", ar -> {
							if (ar.succeeded()) {
								context.response().putHeader("Content-Type", "text/html");
								context.response().end(ar.result());
							} else {
								context.fail(ar.cause());
							}
						});

					} else {
						context.fail(res.cause());
					}
				});
			} else {
				context.fail(car.cause());
			}
		});
	}

}
