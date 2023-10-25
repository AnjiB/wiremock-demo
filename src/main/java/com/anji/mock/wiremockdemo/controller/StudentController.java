package com.anji.mock.wiremockdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anji.mock.wiremockdemo.entity.Student;
import com.anji.mock.wiremockdemo.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	
	@PostMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student saveStudent(@RequestBody Student s) {
		return studentService.save(s);
	}
	
	
	@GetMapping("/student/{rollNum}")
	public ResponseEntity<Student> getStudent(@PathVariable Long rollNum) {
		Student stud = studentService.findByRollNumber(rollNum);
		if(stud != null) {
			return new ResponseEntity<Student>(stud, HttpStatus.OK);
		} else {
			
			Student student = new Student();
			student.setMessage("Student does not exist");
			
			return new ResponseEntity<Student>(student, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value = "/student/{rollNum}", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Student update(@PathVariable Long rollNum, @RequestBody Student name) {
		return studentService.update(rollNum, name);
	}
	
	@DeleteMapping("/student/{rollNum}")
	public void delete(@PathVariable Long rollNum) {
		studentService.delete(rollNum);
	}

}
