package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Subject;

@Repository

public interface SubjectRepository extends JpaRepository<Subject, Long> { 
	
}

