package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ShortTimeout implements RequestHandler<Map<String, Object>, Person> {

  private static final Logger LOG = LogManager.getLogger(ShortTimeout.class);

  @Override
  public Person handleRequest(Map<String, Object> input, Context context) {
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new Person("Duke", true, 42);
  }
}
