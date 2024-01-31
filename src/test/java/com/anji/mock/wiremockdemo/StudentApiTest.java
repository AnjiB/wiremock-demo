package com.anji.mock.wiremockdemo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.anji.mock.wiremockdemo.util.ResourceUtil;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@TestInstance(Lifecycle.PER_CLASS)
class StudentApiTest {

  @RegisterExtension
  private WireMockExtension staticPortConfig = WireMockExtension.newInstance()
      .options(wireMockConfig().port(9191))
      .build();

  @BeforeAll
  void setUp() {
    RestAssured.baseURI = staticPortConfig.getRuntimeInfo().getHttpBaseUrl();
  }

  @Test
  void allStudentsGetApiTest() throws IOException {

    String expRespnse = ResourceUtil.getFileContent("/testdata/allstudents-response.json");

    staticPortConfig.stubFor(get(urlEqualTo("/student")).willReturn(
        aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(expRespnse)));

    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student").get();

    Assertions.assertThat(response.getContentType()).isEqualTo("application/json");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    Assertions.assertThat(response.getBody().asString()).isEqualTo(expRespnse);

  }


  @Test
  void particularStudentGetApiTest() throws IOException {

    String expRespnse = "[\n"
        + "    {\n"
        + "        \"studentId\": 1,\n"
        + "        \"rollNumber\": 127,\n"
        + "        \"name\": \"Anji\"\n"
        + "    }\n"
        + "]";

    staticPortConfig.stubFor(get(urlEqualTo("/student/1")).willReturn(
        aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(expRespnse)));

    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student/1").get();

    Assertions.assertThat(response.getContentType()).isEqualTo("application/json");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    Assertions.assertThat(response.getBody().asString()).isEqualTo(expRespnse);

  }

  @Test
  void studentWhoDoesNotExistTest() throws IOException {

    String expRespnse = "{\n"
        + "    \"message\": \"Student does not exist\"\n"
        + "}";

    staticPortConfig.stubFor(get(urlEqualTo("/student/x")).willReturn(
        aResponse().withStatus(500).withHeader("Content-Type", "application/json").withBody(expRespnse)));

    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student/x").get();

    Assertions.assertThat(response.getContentType()).isEqualTo("application/json");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(500);

    Assertions.assertThat(response.getBody().asString()).isEqualTo(expRespnse);

  }


  @Test
  void createStudentPostRequestTest() {

    String requestString = "{\n"
        + "  \"rollNumber\": 127,\n"
        + "  \"name\": \"Anji\"\n"
        + "}";

    String responseString = "{\n"
        + "    \"studentId\": 1,\n"
        + "    \"rollNumber\": 127,\n"
        + "    \"name\": \"Anji\"\n"
        + "}";


    staticPortConfig.stubFor(post(urlEqualTo("/student"))
        .withHeader("Content-Type", equalTo("application/json"))
        .withRequestBody(equalToJson(requestString))
        .willReturn(
            aResponse().withStatus(200)
                  .withHeader("Content-Type", "application/json")
                  .withBody(responseString)));


    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student")
        .header("Content-Type", "application/json")
        .body(requestString)
        .post();

    Assertions.assertThat(response.getContentType()).isEqualTo("application/json");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    Assertions.assertThat(response.getBody().asString()).isEqualTo(responseString);

  }



  @Test
  void updateStudentNamePutRequestTest() {

    String requestString = "{ \n"
        + "    \"name\": \"Anji Babu\"\n"
        + "}";

    String responseString = "{\n"
        + "    \"studentId\": 1,\n"
        + "    \"rollNumber\": 127,\n"
        + "    \"name\": \"Anji Babu\"\n"
        + "}";


    staticPortConfig.stubFor(put(urlEqualTo("/student/127"))
        .withHeader("Content-Type", equalTo("application/json"))
        .withRequestBody(equalToJson(requestString))
        .willReturn(
            aResponse().withStatus(200)
                  .withHeader("Content-Type", "application/json")
                  .withBody(responseString)));


    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student/127")
        .header("Content-Type", "application/json")
        .body(requestString)
        .put();

    Assertions.assertThat(response.getContentType()).isEqualTo("application/json");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    Assertions.assertThat(response.getBody().asString()).isEqualTo(responseString);

  }


  @Test
  void removeStudentDeleteRequestTest() {


    staticPortConfig.stubFor(delete(urlEqualTo("/student/127"))
        .willReturn(
            aResponse().withStatus(200)
                  .withBody("")));


    RequestSpecification httpRequest = RestAssured.given();

    Response response = httpRequest.basePath("/student/127")
        .delete();


    Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    Assertions.assertThat(response.getBody().asString()).isEqualTo("");

  }



}
