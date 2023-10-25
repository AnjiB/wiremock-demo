package com.anji.mock.wiremockdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anji.mock.wiremockdemo.entity.StudentMarks;
import com.anji.mock.wiremockdemo.repository.StudentMarksRepository;

@Service
public class StudentMarksServiceImpl implements StudentMarksService {

	@Autowired
	private StudentMarksRepository studentMarksRepository;
	
	@Override
	public StudentMarks save(StudentMarks marks) {
		return studentMarksRepository.save(marks);
	}

}
