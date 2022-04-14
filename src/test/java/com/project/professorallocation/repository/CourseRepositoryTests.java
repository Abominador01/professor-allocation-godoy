package com.project.professorallocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTests {
	@Autowired
	CourseRepository repository;

	@Test
	public void findAll() {
		List<Course> items = repository.findAll();

		System.out.println("Qtd elementos retornados :" + items.size());

		for (Course item : items) {
			System.out.println(item);

		}
	}

	@Test
	public void findSpecificCourse() {
		Course course = repository.findById(8L).orElse(null);
		System.out.println(course);

	}
	@Test
	public void create() {

		Course CourseBeingCreated = new Course();
		CourseBeingCreated.setName("Curso de Informática");

		CourseBeingCreated = repository.save(CourseBeingCreated);
		System.out.println(CourseBeingCreated);
	}

	@Test
	public void update() {
		Course CourseBeingCreated = new Course();
		CourseBeingCreated.setId(5L);
		CourseBeingCreated.setName("Curso de Fisioterapia");

		CourseBeingCreated = repository.save(CourseBeingCreated);
		System.out.println(CourseBeingCreated);
	}

	@Test
	public void delete() {
		repository.deleteById(6L);

	}

	@Test
	public void deleteAllItems() {
		repository.deleteAllInBatch();
	}
}
