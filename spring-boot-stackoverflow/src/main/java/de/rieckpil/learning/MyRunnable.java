package de.rieckpil.learning;

public class MyRunnable implements Runnable {

  private final MyInterface myInterface;

  public MyRunnable(MyInterface myInterface) {
    this.myInterface = myInterface;
  }

  @Override
  public void run() {
    // ... do your logic here
  }
}
