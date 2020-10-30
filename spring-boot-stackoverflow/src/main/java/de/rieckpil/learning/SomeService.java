package de.rieckpil.learning;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SomeService {

  private final TaskExecutor taskExecutor;
  private final MyInterface myInterface;

  public SomeService(MyInterface myInterface, TaskExecutor taskExecutor) {
    this.myInterface = myInterface;
    this.taskExecutor = taskExecutor;
  }

  public String doFoo() {

    taskExecutor.execute(new MyRunnable(myInterface));

    return myInterface.getName();
  }

}
