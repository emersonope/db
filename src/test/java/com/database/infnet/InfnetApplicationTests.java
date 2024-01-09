package com.database.infnet;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {InfnetApplication.class, TestConfig.class})
@TestPropertySource(properties = {"spring.application.servernick=test-server-nick"})
class InfnetApplicationTests {

	@Value("${spring.application.servernick}")
	private String ServerNick;

	@Test
	public void contextLoads() {
		System.out.println(MobyNamesGenerator.getRandomName());
	}

}
