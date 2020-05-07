package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, Person> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public Person handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: {}", input);
		LOG.error("error!");
		LOG.debug("debug!");
		LOG.info(System.getenv("GLOBAL_SECRET"));
		LOG.info(System.getenv("FUNCTION_SECRET"));
		return new Person("Duke", true, 42);
	}
}
