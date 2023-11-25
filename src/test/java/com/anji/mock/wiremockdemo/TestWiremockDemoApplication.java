package com.anji.mock.wiremockdemo;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Disabled
@TestConfiguration(proxyBeanMethods = false)
public class TestWiremockDemoApplication {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(WiremockDemoApplication::main).with(TestWiremockDemoApplication.class).run(args);
	}

}
