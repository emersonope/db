package com.database.infnet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	@Value("${spring.application.servernick}")
	private String serverNick;

	@Bean
	public String serverNick() {
		return serverNick;
	}
}
