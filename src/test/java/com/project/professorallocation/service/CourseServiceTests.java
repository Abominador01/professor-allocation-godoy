package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTests {

	@Autowired
	CourseService service;

	@Test
	public void create() {

		Course course = new Course();
		course.setName("Biologia");

		course = service.create(course);
	}

	@Test
	public void update() {
		Course course = new Course();
		course.setId(4L);
		course.setName("ed.fisica");

		course = service.update(course);

	}

	@Test
	public void findAll() {
		List<Course> course = service.findAll(null);

		course.forEach(System.out::println);
	}

	@Test
	public void findSpecificCourse() {
		Long id = 4L;
		Course course = service.findById(id);
		System.out.println(course);

	}

	@Test
	public void deleteById() {
		service.deleteById(3L);

	}
}
