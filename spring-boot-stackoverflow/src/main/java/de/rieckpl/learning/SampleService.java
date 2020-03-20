package de.rieckpl.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  @Autowired
  private OtherService otherService;

  public String getData() {
    return otherService.doFoo();
  }

  @Scheduled(cron = "${rieckpil.cron}")
  public void doFoo() {
    System.out.println("FOO!");
  }
}
