package de.rieckpil.learning.javadoc;

public class JavaDocExample {

  public static void main(String[] args) {
    JavaDocExample example = new JavaDocExample();
    example.sayHello();
  }
  
  /**
   * This method returns <pre>{@code System.out.println("Hallo");}</pre>
   * {@literal |r| < 1}
   * 
   * @return
   */
  public String sayHello() {
    return "Hello Word!";
  }
  
}
