package kryo;

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable {

    public String name;
    public int age;
    public Instant time;
    public double amount;
    public boolean isNew;

    public Message() {
    }

    public Message(String name, int age, Instant time, double amount, boolean isNew) {
        this.name = name;
        this.age = age;
        this.time = time;
        this.amount = amount;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", time=" + time +
                ", amount=" + amount +
                ", isNew=" + isNew +
                '}';
    }
}
