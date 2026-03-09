package com.jicjo.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@PropertySource("application.yaml")
public class JicPortalApiBackOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JicPortalApiBackOfficeApplication.class, args);
	}

}
