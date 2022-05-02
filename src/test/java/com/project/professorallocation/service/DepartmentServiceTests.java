package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTests {

	@Autowired
	DepartmentService service;

	@Test
	public void create() {

		Department department = new Department();
		department.setName("Departamento de marketing");

		department = service.create(department);
	}

	@Test
	public void update() {
		Department department = new Department();
		department.setId(4L);
		department.setName("Departamento de Fisioterapia");

		department = service.update(department);

	}

	@Test
	public void findAll() {
		List<Department> department = service.findAll(null);
		department.forEach(System.out::println);

	}

	@Test
	public void findSpecificDepartment() {

		Long id = 2L;
		Department department = service.findById(id);
		System.out.println(department);

	}

	@Test
	public void delete() {
		Long id = 4L;
		service.deleteById(id);
	}

}
