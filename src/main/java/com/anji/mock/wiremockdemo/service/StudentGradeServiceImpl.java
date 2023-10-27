package com.anji.mock.wiremockdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anji.mock.wiremockdemo.entity.StudentMarks;
import com.anji.mock.wiremockdemo.nums.Grade;
import com.anji.mock.wiremockdemo.repository.StudentMarksRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentGradeServiceImpl implements StudentGradeService {

	@Autowired
	private StudentMarksRepository studentMarksRepository;

	@Override
	public String calculateGrade(Long studentId) {

		StudentMarks studentMarks = studentMarksRepository.findByStudentStudentId(studentId);

		Double totalMarks = studentMarks.getMaths() + studentMarks.getScience() + studentMarks.getEnglish();

		log.info("Total Marks {}", totalMarks);
		
		return findGrade(totalMarks / 3).toString();

	}

	private Grade findGrade(Double totalMarks) {

		if (totalMarks >= 80)
			return Grade.A;
		else if (totalMarks > 60 && totalMarks < 80)
			return Grade.B;
		else
			return Grade.C;
	}

}
