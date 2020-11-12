package de.rieckpil.learning.config;

import de.rieckpil.learning.interceptor.TestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private TestInterceptor testInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/public/**")
      .allowedOrigins("https://foo.bar")
      .allowedMethods("GET", "PUT", "DELETE")
      .allowedHeaders("Accept", "Authorization", "Content-Type")
      .exposedHeaders("Content-Type", "header2")
      .allowCredentials(true).maxAge(3600);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(testInterceptor);
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
    crlf.setIncludeClientInfo(true);
    crlf.setIncludeQueryString(true);
    crlf.setIncludePayload(true);
    crlf.setIncludeHeaders(true);
    return crlf;
  }
}
