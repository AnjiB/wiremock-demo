package com.anji.mock.wiremockdemo;

import static java.lang.String.valueOf;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.response.Response;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class StudentGradeIntegrationTest {

  @Container
  private static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
      .withInitScript("testdata/student_test_data.sql");
      //.withStartupTimeout(Duration.ofMinutes(1))
        //    .withConnectTimeoutSeconds(60);


  private static final String HOST = "http://localhost:";

  @LocalServerPort
  private int port;

  @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

    System.out.println(mysqlContainer.getJdbcUrl());

        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

  // start container
    //@BeforeAll
    static void beforeAll() {
      mysqlContainer.start();
      System.out.println(mysqlContainer.getJdbcUrl());
    }

    // stop container
    //@AfterAll
    static void afterAll() {
      mysqlContainer.stop();
    }


  @Test
  void testGrade() {

    StudentTestClient studentTestClient = new StudentTestClient(HOST + valueOf(port));
    Response gradeResponse = studentTestClient.getGrade(1L);
    Assertions.assertThat(gradeResponse.statusCode()).isEqualTo(200);
    Assertions.assertThat(gradeResponse.asPrettyString()).isEqualTo("{\n"
        + "    \"grade\": \"B\"\n"
        + "}");
  }
}
