package com.anji.mock.wiremockdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anji.mock.wiremockdemo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student> findByRollNumber(Long rollNumber);
	
	
	void deleteByRollNumber(Long rollNumber);
}
