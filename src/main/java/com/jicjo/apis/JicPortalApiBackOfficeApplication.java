package com.jicjo.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
//@PropertySource("application.yaml")
public class JicPortalApiBackOfficeApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JicPortalApiBackOfficeApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JicPortalApiBackOfficeApplication.class, args);
	}

}
