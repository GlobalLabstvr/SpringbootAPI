package com.example.easynotes.resource;


import org.springframework.hateoas.ResourceSupport;

import com.example.easynotes.model.Course;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseResource extends ResourceSupport {

	private final long id;
	private final String name;
	
	private final String description;
	
	public CourseResource(Course course) {
		id = course.getId();
		name = course.getName();
		
		description = course.getDescription();
		
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}


	}
