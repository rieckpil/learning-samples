package de.rieckpil.learning.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameMatch, Object> {
  private String firstFieldName;
  private String secondFieldName;

  @Override
  public void initialize(final NameMatch constraintAnnotation) {
    firstFieldName = constraintAnnotation.first();
    secondFieldName = constraintAnnotation.second();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    boolean isValidName = false;
    try {
      //final Object firstName = BeanUtils.getProperty(value, firstFieldName);
      // final Object lastName = BeanUtils.getProperty(value, secondFieldName);

      // Validation logic

    } catch (final Exception ignore) {
    }
    return isValidName;
  }
}
