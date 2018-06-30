package de.rieckpil.javaee8;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;
import java.util.logging.Logger;

@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Singleton
public class FlywayMigration {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Resource(lookup = "jdbc/sample")
    DataSource dataSource;

    @PostConstruct
    private void onStartup() {

        if (dataSource == null) {
            log.severe("no datasource found to execute the db migrations!");
            throw new RuntimeException(
                    "no datasource found to execute the db migrations!");
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        for (MigrationInfo i : flyway.info().all()) {
            log.info("migrate task: " + i.getVersion() + " : "
                    + i.getDescription() + " from file: " + i.getScript());
        }
        flyway.migrate();

    }
}
