package com.anji.mock.wiremockdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
	
    private Long rollNumber;
    
    private String name;
    
    private String message;
}
