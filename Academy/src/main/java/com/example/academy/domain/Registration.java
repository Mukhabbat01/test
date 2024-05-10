package com.example.academy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int regisId;
	
	@Column(length = 20, nullable = false)
	private String studentPhone;
	
	@Column(length = 50, nullable = false)
	private String studentName;
	
	@ManyToOne
	@JoinColumn(name = "lec_id")
	private Lecture lecture;
}
