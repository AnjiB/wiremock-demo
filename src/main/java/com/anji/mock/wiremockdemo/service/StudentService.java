package com.anji.mock.wiremockdemo.service;

import java.util.List;

import com.anji.mock.wiremockdemo.entity.Student;

public interface StudentService {

	Student save(Student student);

    List<Student> getAllStudents();

    Student findByRollNumber(Long rollNumber);
    
    Student update(Long rollNumber, Student name);
    
    void delete(Long rollNumber);
}
