package de.rieckpil.learning;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CustomProblem extends AbstractThrowableProblem {

  public CustomProblem(String title) {
    super(null, title, Status.SERVICE_UNAVAILABLE);
  }
}
