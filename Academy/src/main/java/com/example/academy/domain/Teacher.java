package com.example.academy.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher {
	@Id
	@Column(length=20, nullable = false)
	private String teacherId;
	
	@Column(length = 50, nullable = false)
	private String teacherName;
	
	@OneToMany(mappedBy = "teacher")
	private List<Lecture> lecture;
}
