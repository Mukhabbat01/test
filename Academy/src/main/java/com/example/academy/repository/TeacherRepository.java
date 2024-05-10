package com.example.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.academy.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,String>{

	

	
}
