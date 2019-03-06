package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Subject;
import com.example.easynotes.repository.SubjectRepository;

@RestController
@RequestMapping("/api")
public class SubjectController {
	@Autowired
	 SubjectRepository subjectRepository;
	
	
	@GetMapping("/subject")
	public List<Subject> getAllCourse()
	{
		return subjectRepository.findAll();
	}
	
	 @PostMapping("/subject")
	    public Subject createSubject(@Valid @RequestBody Subject subject) {
	        return subjectRepository.save(subject);
	    }

	    @GetMapping("/subject/{id}")
	    public Subject getSubjectById(@PathVariable(value = "id") Long subjectId) {
	        return subjectRepository.findById(subjectId)
	                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));
	    }

	    @PutMapping("/subject/{id}")
	    public Subject updateSubject(@PathVariable(value = "id") Long subjectId,
	                                           @Valid @RequestBody Subject subjectDetails) {

	        Subject subject = subjectRepository.findById(subjectId)
	                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));

	        subject.setName(subjectDetails.getName());
	        subject.setDescription(subjectDetails.getDescription());

	        Subject updatedSubject = subjectRepository.save(subject);
	        return updatedSubject;
	    }

	    @DeleteMapping("/subject/{id}")
	    public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") Long subjectId) {
	        Subject subject = subjectRepository.findById(subjectId)
	                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));

	        subjectRepository.delete(subject);

	        return ResponseEntity.ok().build();
	    }
	}

