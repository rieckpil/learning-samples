package de.rieckpil.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protobuf")
@Profile("protobuf")
public class ProtobufController {

  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    return new ProtobufHttpMessageConverter();
  }

  @PostMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
  public String doProto() {
    return "duke";
  }
}
