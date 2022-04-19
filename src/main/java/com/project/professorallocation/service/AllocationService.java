package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository repository;

	public AllocationService(AllocationRepository repository) {
		super();
		this.repository = repository;
	}

	public Allocation findById(Long Id) {
		return repository.findById(Id).orElse(null);

	}

	public List<Allocation> findAll() {
		return repository.findAll();

	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);

	}

	private Allocation saveInternal(Allocation allocation) {
		Allocation insertedAllocation = repository.save(allocation);
		return insertedAllocation;
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id == null || !repository.existsById(id)) {
			return null;
		} else {
			return saveInternal(allocation);

		}
	}
}
