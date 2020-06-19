package de.rieckpl.learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/public/**")
      .allowedOrigins("https://foo.bar")
      .allowedMethods("GET", "PUT", "DELETE")
      .allowedHeaders("Accept", "Authorization", "Content-Type")
      .exposedHeaders("Content-Type", "header2")
      .allowCredentials(true).maxAge(3600);
  }
}
