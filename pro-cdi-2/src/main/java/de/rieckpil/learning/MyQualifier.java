package de.rieckpil.learning;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
@Documented
public @interface MyQualifier {
    final class Literal extends AnnotationLiteral<MyQualifier> implements MyQualifier {
        private static final long serialVersionUID = 1L;
        public static final Literal INSTANCE = new Literal();
    }
}