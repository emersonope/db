package com.database.infnet;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class InfnetApplication {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(InfnetApplication.class);
		Properties properties = new Properties();
		String appNickName = MobyNamesGenerator.getRandomName();
		properties.put("spring.application.servernick", appNickName);
		application.setDefaultProperties(properties);
		application.run(args);
	}

}
