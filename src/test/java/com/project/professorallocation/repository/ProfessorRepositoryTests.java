package com.project.professorallocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTests {
	@Autowired
	ProfessorRepository repository;

	@Test
	public void findAll() {
		List<Professor> items = repository.findAll();

		System.out.println("Qtd elementos retornados :" + items.size());

		for (Professor item : items) {
			System.out.println(item);

		}
	}

	@Test
	public void findSpecificProfessor() {
		Professor professor = repository.findById(8L).orElse(null);
		System.out.println(professor);

	}
	@Test
	public void create() {

		Professor ProfessorBeingCreated = new Professor();
		ProfessorBeingCreated.setName("José");

		ProfessorBeingCreated = repository.save(ProfessorBeingCreated);
		System.out.println(ProfessorBeingCreated);
	}

	@Test
	public void update() {
		Professor ProfessorBeingCreated = new Professor();
		ProfessorBeingCreated.setId(5L);
		ProfessorBeingCreated.setName("Alexandre");

		ProfessorBeingCreated = repository.save(ProfessorBeingCreated);
		System.out.println(ProfessorBeingCreated);
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

