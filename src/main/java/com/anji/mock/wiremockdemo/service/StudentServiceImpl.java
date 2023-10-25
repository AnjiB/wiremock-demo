package com.anji.mock.wiremockdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anji.mock.wiremockdemo.entity.Student;
import com.anji.mock.wiremockdemo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findByRollNumber(Long rollNumber) {
		List<Student> existingStudents = studentRepository.findByRollNumber(rollNumber);
		
		if(existingStudents.size() > 0)
			return studentRepository.findByRollNumber(rollNumber).get(0);
		
		else
			return null;
	}

	@Override
	public Student update(Long rollNumber, Student name) {

		List<Student> existingStudents = studentRepository.findByRollNumber(rollNumber);
				//findByRollNumber(rollNumber);

		if (existingStudents.size() != 0) {
			
			Student existingStudent = existingStudents.get(0);

			existingStudent.setName(name.getName());

			return studentRepository.save(existingStudent);

		} else {
			name.setRollNumber(rollNumber);
			return studentRepository.save(name);
		}
		
	}

	@Override
	@Transactional
	public void delete(Long rollNumber) {
		studentRepository.deleteByRollNumber(rollNumber);

	}
}
