package com.project.professorallocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.service.CourseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/Courses")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Find all courses")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "nm", required = false) String nome) {
		List<Course> allCourses = service.findAll(nome);

		return new ResponseEntity<>(allCourses, HttpStatus.OK);

	}

	@ApiOperation(value = "Finds an course by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 404, message = "Course not found")
	})
	@GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> findById(@PathVariable(name = "Course_id") Long id) {
		Course item = service.findById(id);
		if (item == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create a new Course ")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "New Course created"),
		@ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Course> create(@RequestBody Course course) {
		try {
			Course item = service.create(course);

			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Update an existing Course")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Course updated"),
		@ApiResponse(code = 404, message = "Course not found"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PutMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id, @RequestBody Course course) {
		try {
			course.setId(id);
			Course item = service.update(course);

			if (item == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			} else {
				return new ResponseEntity<Course>(item, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}

	@ApiOperation(value = "Delete an Course by id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Course Deleted") })
	@DeleteMapping(path = "/{course_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable(name = "course_id") Long id) {
		service.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
