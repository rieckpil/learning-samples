package de.rieckpil.learning.reflectiondemo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SampleClass {

    @NotNull
    @Size(max = 500, min = 100)
    private String field1;
    private double field2;
    private boolean field3;

    @Autowired
    private SampleClass() {
    }

    public SampleClass(double field2) {
        this.field2 = field2;
    }

    public SampleClass(String field1, double field2, boolean field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public void doFoo(String x) {
        System.out.println(x);
    }

    public String doBar(int i) {
        return "Hello World " + i;
    }
}
