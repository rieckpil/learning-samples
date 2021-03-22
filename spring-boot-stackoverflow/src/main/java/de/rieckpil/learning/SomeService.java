package de.rieckpil.learning;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SomeService {

  private final TaskExecutor taskExecutor;
  private final MyInterface myInterface;

  public SomeService(MyInterface myInterface, TaskExecutor brokerChannelExecutor) {
    this.myInterface = myInterface;
    this.taskExecutor = brokerChannelExecutor;
  }

  public String doFoo() {

    taskExecutor.execute(new MyRunnable(myInterface));

    return myInterface.getName();
  }

  public String doRequest() {
    HttpResponse<JsonNode> response = Unirest
      .post("http://httpbin.org/post")
      .header("accept", "application/json")
      .queryString("apiKey", "123")
      .field("parameter", "value")
      .field("firstname", "Gary")
      .asJson();

    return response.getStatusText();
  }

  public void doBar() {
    System.out.println(LocalDateTime.now());
    System.out.println(LocalDateTime.of(2020, 12, 31, 12, 0));
  }

}
