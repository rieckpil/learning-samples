package de.rieckpil.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

  @Bean
  public ITemplateResolver svgTemplateResolver() {
    SpringResourceTemplateResolver resolver = new
      SpringResourceTemplateResolver();
    resolver.setPrefix("classpath:/templates/svg/");
    resolver.setSuffix(".svg");
    resolver.setTemplateMode("XML");
    return resolver;
  }
}
