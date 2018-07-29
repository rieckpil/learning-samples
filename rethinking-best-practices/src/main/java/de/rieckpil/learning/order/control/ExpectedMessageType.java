package de.rieckpil.learning.order.control;

import javax.jms.Message;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExpectedMessageType {
    Class<? extends Message> value();
}
