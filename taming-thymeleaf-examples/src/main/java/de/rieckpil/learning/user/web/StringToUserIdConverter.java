package de.rieckpil.learning.user.web;

import de.rieckpil.learning.user.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToUserIdConverter implements Converter<String, UserId> {
  @Override
  public UserId convert(String s) {
    return new UserId(UUID.fromString(s));
  }
}
