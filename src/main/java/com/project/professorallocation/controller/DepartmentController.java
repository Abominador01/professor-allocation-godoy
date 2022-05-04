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

import com.project.professorallocation.model.Department;
import com.project.professorallocation.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	private final DepartmentService service;

	public DepartmentController(DepartmentService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Find all allocations")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "nm", required = false) String nome) {
		List<Department> allDepartments = service.findAll(nome);

		return new ResponseEntity<>(allDepartments, HttpStatus.OK);

	}

	@ApiOperation(value = "Finds an department by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 404, message = "department not found")
	})
	@GetMapping(path = "/{dept_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> findById(@PathVariable(name = "dept_id") Long id) {
		Department item = service.findById(id);
		if (item == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create a new department ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "New department created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Department> create(@RequestBody Department dept) {
	try {	
		Department item = service.create(dept);

		return new ResponseEntity<>(item, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	}

	@ApiOperation(value = "Update an existing department")
	@ApiResponses({
		@ApiResponse(code = 200, message = "department updated"),
		@ApiResponse(code = 404, message = "department not found"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PutMapping(path = "/{dept_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> update(@PathVariable(name = "dept_id") Long id, @RequestBody Department dept) {
	try {
		dept.setId(id);
		Department item = service.update(dept);

		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<Department>(item, HttpStatus.CREATED);
		}
	} catch (Exception ex) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	}

	@ApiOperation(value = "Delete an department by id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Department Deleted")
	})
	@DeleteMapping(path = "/{dept_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable(name = "dept_id") Long id) {
		service.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
