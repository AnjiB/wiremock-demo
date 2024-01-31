package com.anji.mock.wiremockdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.anji.mock.wiremockdemo.entity.Student;

import io.restassured.response.Response;


public class StudentGradeE2ETest {


  private static final String STUDENT_APP_BASE_URI = "https://www.somecompany.com/student/v1/";

  private static final StudentTestClient studentTestClient = new StudentTestClient(STUDENT_APP_BASE_URI);

  private static Student anji;

  @BeforeAll
  static void setUp() {

    // usually, we send Student Object instead of a String
    String student = "{\n"
        + "  \"rollNumber\": 1,\n"
        + "  \"name\": \"Anji\"\n"
        + "}";


    // create a student
    anji = studentTestClient.createStudent(student);

    String anjiMarks = String.format("{\n"
        + "  \"student\": {\n"
        + "      \"studentId\": %s\n"
        + "  },\n"
        + "  \"maths\": 80,\n"
        + "  \"science\": 70,\n"
        + "  \"english\": 65\n"
        + "}", anji.getStudentId());

    // add some marks
    studentTestClient.addMarks(anjiMarks);

  }

  @Test
  void testGrade() {

    Response gradeResponse = studentTestClient.getGrade(anji.getStudentId());
    Assertions.assertThat(gradeResponse).isEqualTo(200);
    Assertions.assertThat(gradeResponse.asString()).isEqualTo("{\n"
        + "    \"grade\": \"B\"\n"
        + "}");
  }

  @AfterAll
  static void cleanUp() {
    // delete student
  }
}
