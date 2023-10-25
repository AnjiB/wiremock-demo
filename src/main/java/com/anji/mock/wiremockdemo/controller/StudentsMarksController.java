package com.anji.mock.wiremockdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anji.mock.wiremockdemo.entity.StudentMarks;
import com.anji.mock.wiremockdemo.service.StudentMarksService;

@RestController
public class StudentsMarksController {
	
	@Autowired
	private StudentMarksService studentMarksService;

	@PostMapping(value = "/marks", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StudentMarks saveMarks(@RequestBody StudentMarks s) {
		return studentMarksService.save(s);
	}
}
