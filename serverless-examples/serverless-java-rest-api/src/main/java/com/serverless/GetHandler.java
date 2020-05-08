package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

  @Override
  public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> result = new HashMap();
      result.put("statusCode", 200);
      result.put("body", objectMapper.writeValueAsString(new Person("Duke", Set.of("Java", "Gaming"), 42)));
      return result;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
