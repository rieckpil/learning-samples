package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostHandler implements RequestHandler<Person, Map<String, Object>> {

  @Override
  public Map<String, Object> handleRequest(Person input, Context context) {
    Map<String, Object> result = new HashMap();
    result.put("statusCode", 201);
    result.put("body", UUID.randomUUID().toString());
    return result;
  }
}
