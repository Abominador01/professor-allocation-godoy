package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTests {

	@Autowired
	ProfessorService service;

	@Test
	public void create() {

		Professor professor = new Professor();
		professor.setName("godoy");
		professor.setDepartmentId(3L);
		professor.setCpf("14522233345");

		professor = service.create(professor);

	}

	@Test
	public void update() {
		Professor professor = new Professor();
		professor.setId(6L);
		professor.setName("Alexandre");
		professor.setDepartmentId(2L);
		professor.setCpf("12345895911");

		professor = service.update(professor);
	}

	@Test
	public void findById() {
		Long id = 4L;
		Professor professor = service.findById(id);
		System.out.println(professor);
	}

	@Test
	public void findAll() {
		List<Professor> professor = service.findAll(null);

		professor.forEach(System.out::println);
	}

	@Test
	public void deleteById() {
		service.deleteById(6L);

	}

}
