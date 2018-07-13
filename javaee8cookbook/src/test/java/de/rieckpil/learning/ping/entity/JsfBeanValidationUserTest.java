package de.rieckpil.learning.ping.entity;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class JsfBeanValidationUserTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void validUser() {
        BeanValidationUser beanValidationUser = new BeanValidationUser(
                "elder",
                "elder@eldermoraes.com",
                asList(1,2));

        Set<ConstraintViolation<BeanValidationUser>> cv = validator
                .validate(beanValidationUser);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidName() {
        BeanValidationUser beanValidationUser = new BeanValidationUser(
                "",
                "elder@eldermoraes.com",
                asList(1,2));

        Set<ConstraintViolation<BeanValidationUser>> cv = validator
                .validate(beanValidationUser);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidEmail() {
        BeanValidationUser beanValidationUser = new BeanValidationUser(
                "elder",
                "elder-eldermoraes_com",
                asList(1,2));

        Set<ConstraintViolation<BeanValidationUser>> cv = validator
                .validate(beanValidationUser);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidId() {
        BeanValidationUser beanValidationUser = new BeanValidationUser(
                "elder",
                "elder@eldermoraes.com",
                asList(-1,-2,1,2));

        Set<ConstraintViolation<BeanValidationUser>> cv = validator
                .validate(beanValidationUser);
        assertEquals(2, cv.size());
    }
}