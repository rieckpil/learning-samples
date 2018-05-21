package de.rieckpil.learning.springboot2book.christmas;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Conditional(IsChristmasCondition.class)
public class ChristmasBean {

    @PostConstruct
    public void init() {
        System.out.println("It's Christmas time!");
    }
}
