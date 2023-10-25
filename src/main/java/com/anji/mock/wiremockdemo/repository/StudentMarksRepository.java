package com.anji.mock.wiremockdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anji.mock.wiremockdemo.entity.StudentMarks;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long>{

}
