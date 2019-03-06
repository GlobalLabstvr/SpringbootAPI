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
import com.example.easynotes.model.Course;
import com.example.easynotes.repository.CourseRepository;

import com.example.easynotes.repository.SubjectRepository;

@RestController
@RequestMapping("/api")
public class CourseController {
	@Autowired
	 CourseRepository courseRepository;
	
	
	@Autowired
	 SubjectRepository subjectRepository;
	
	@GetMapping("/course")
	public List<Course> getAllCourse()
	{
		return courseRepository.findAll();
	}
	
	 @PostMapping("/course")
	    public Course createCourse(@Valid @RequestBody Course course) {
	        return courseRepository.save(course);
	    }

	    @GetMapping("/course/{id}/subject/{id}")
	    public Course getCourseById(@PathVariable(value = "id") Long courseId) {
	        return courseRepository.findById(courseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
	    }

	    @PutMapping("/course/{id}/subject/{id}")
	    public Course updateCourse(@PathVariable(value = "id") Long courseId,
	                                           @Valid @RequestBody Course courseDetails) {

	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

	        course.setName(courseDetails.getName());
	        course.setDescription(courseDetails.getDescription());

	        Course updatedCourse = courseRepository.save(course);
	        return updatedCourse;
	    }

	    @DeleteMapping("/course/{id}/subject/{id}")
	    public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") Long courseId) {
	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

	        courseRepository.delete(course);

	        return ResponseEntity.ok().build();
	    }
	    
	    


	   
	}
	


