package com.anji.mock.wiremockdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentMarks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long marksId;

	@OneToOne
	@JoinColumn(name = "studentId")
	private Student student;

	private Double maths;

	private Double science;

	private Double english;

}
