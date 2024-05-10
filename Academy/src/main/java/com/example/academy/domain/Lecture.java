package com.example.academy.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lecId;
	
	@Column(length=50, nullable=false) 
	private String title;
	
	@Column(nullable = false)
	private int maxStudent;
	
	@Column(nullable = false)
	private LocalDateTime startDtm;
	
	@Column(length = 30, nullable = false)
	private String room;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy="lecture")
	private List<Registration> registration;
}
