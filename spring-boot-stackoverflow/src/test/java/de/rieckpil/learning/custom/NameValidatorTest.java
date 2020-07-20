package de.rieckpil.learning.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameValidatorTest {

  @Mock
  private NameMatch nameMatch;

  @Mock
  private ConstraintValidatorContext constraintValidatorContext;

  @Test
  public void testIsValid() {

    when(nameMatch.first()).thenReturn("firstname");
    when(nameMatch.second()).thenReturn("lastname");

    System.out.println(nameMatch.first());
    System.out.println(nameMatch.second());

    NameValidator nameValidator = new NameValidator();
    nameValidator.initialize(nameMatch);
  }

}
