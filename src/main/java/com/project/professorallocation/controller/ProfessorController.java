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

import com.project.professorallocation.model.Professor;
import com.project.professorallocation.service.ProfessorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

	private final ProfessorService service;

	public ProfessorController(ProfessorService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Find all professors")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "nm", required = false) String nome) {
		List<Professor> allProfessors = service.findAll(nome);

		return new ResponseEntity<>(allProfessors, HttpStatus.OK);

	}

	@ApiOperation(value = "Finds an professor by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 404, message = "Professor not found")
	})
	@GetMapping(path = "/{prof_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> findById(@PathVariable(name = "prof_id") Long id) {
		Professor item = service.findById(id);
		if (item == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create a new professor ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "New professor created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Professor> create(@RequestBody Professor prof) {
		try {
		Professor item = service.create(prof);

		return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		}

	@ApiOperation(value = "Update an existing professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "professor updated"),
		@ApiResponse(code = 404, message = "professor not found"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PutMapping(path = "/{prof_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> update(@PathVariable(name = "prof_id") Long id, @RequestBody Professor prof) {
	try {
		prof.setId(id);
		Professor item = service.update(prof);

		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<Professor>(item, HttpStatus.CREATED);
		}
	} catch (Exception ex) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	}

	@ApiOperation(value = "Delete an professor by id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Professor Deleted")
	})
	@DeleteMapping(path = "/{prof_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable(name = "prof_id") Long id) {
		service.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
