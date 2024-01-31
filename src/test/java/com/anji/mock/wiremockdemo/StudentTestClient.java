package com.anji.mock.wiremockdemo;

import com.anji.mock.wiremockdemo.entity.Student;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StudentTestClient {

  private String baseURL;

  public StudentTestClient(String baseURL) {
    this.baseURL = baseURL;
  }

  private RequestSpecification getSpec() {
    return RestAssured.given().baseUri(baseURL);
  }


  public Student createStudent(Object student) {

    Response createStudentResponse = getSpec()
        .basePath("/student")
        .contentType(ContentType.JSON)
        .body(student).post();

    return createStudentResponse.as(Student.class);

  }


  public void addMarks(Object marks) {

    Response addMarks = getSpec()
        .basePath("/marks")
        .contentType(ContentType.JSON)
        .body(marks).post();

    if(addMarks.statusCode() != 200)
      throw new RuntimeException("Not able to add marks to the student" + addMarks.asString());
  }


  public Response getGrade(Long studentId) {
    return  getSpec()
        .basePath("/grade")
        .queryParam("studentId", studentId)
        .contentType(ContentType.JSON)
        .get();
  }
}
