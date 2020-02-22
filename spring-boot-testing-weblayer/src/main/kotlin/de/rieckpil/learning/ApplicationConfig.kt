package de.rieckpil.learning

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ApplicationConfig {

  @Bean
  @Primary
  fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
    return builder
      .createXmlMapper(false)
      .propertyNamingStrategy(PropertyNamingStrategy.LOWER_DOT_CASE)
      .build()
  }
}
