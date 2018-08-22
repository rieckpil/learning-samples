package de.rieckpil.learning.springbootmultidatasources;

public class Test {

    private Long id;
    private String text;

    public Test() {
    }

    public Test(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
