package de.rieckpil.learning;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class UmWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmWebappApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<Filter> someFilterRegistration() {
		final FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setFilter(etagFilter());
		registration.setName("etagFilter");
		registration.setOrder(1);
		return registration;
	}
	
	@Bean(name = "etagFilter")
	public Filter etagFilter() {
		return new ShallowEtagHeaderFilter();
	}
}
