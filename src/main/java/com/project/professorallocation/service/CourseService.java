package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository repository;

	public CourseService(CourseRepository repository) {
		super();
		this.repository = repository;
	}

	private Course saveInternal(Course course) {
		Course insertedCourse = repository.save(course);
		return insertedCourse;
	}

	public Course create(Course course) {
		course.setId(null);
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id == null || !repository.existsById(id)) {
			return null;
		} else {
			return saveInternal(course);
		}
	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public Course findById(Long Id) {
		return repository.findById(Id).orElse(null);

	}

	public List<Course> findAll(String name) {
		if(name == null || name.trim().equals("")) {
			
			return repository.findAll();
		}else {
			
			return repository.findByNameContaining(name);
		}
		

	}
}
