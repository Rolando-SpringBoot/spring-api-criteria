package com.enagas.msc.producto;

import org.springframework.boot.SpringApplication;
import com.enagas.arch.core.annotations.EnaApplication;

/**
 * Spring Boot main application class.
 */
@EnaApplication
public class Application {

	/**
	 * The main method to start the Spring Boot application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}