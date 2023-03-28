package com.example.userservicedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@Log4j2
@SpringBootApplication
public class UserservicedemoApplication {

	private static final Logger log= LogManager.getLogger(UserservicedemoApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(UserservicedemoApplication.class, args);


		// level

		log.trace("This is trace message");
		log.debug("This is debug message");
		log.info("This is an info message");
		log.warn("This is an warn message");
		log.error("This is an error message");
		log.fatal("This is an fatal message");
	}
}
