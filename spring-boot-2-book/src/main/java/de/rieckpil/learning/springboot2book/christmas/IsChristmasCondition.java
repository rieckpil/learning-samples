package de.rieckpil.learning.springboot2book.christmas;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.time.LocalDate;

public class IsChristmasCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return LocalDate.now().isEqual(LocalDate.now().withMonth(12).withDayOfMonth(24));
    }
}
