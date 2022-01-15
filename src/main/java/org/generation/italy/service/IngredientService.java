package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Ingredient;
import org.generation.italy.model.Pizza;
import org.generation.italy.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	@Autowired
	private IngredientRepository repository;

	public List<Ingredient> findAllSortedByName() {
		return repository.findAll(Sort.by("name"));
	}

	public List<Ingredient> findByKeywordSortedByName(String keyword) {
		return repository.findByNameContainingIgnoreCaseOrderByName(keyword);
	}

	public Ingredient save(Ingredient ingredient) {
		return repository.save(ingredient);
	}

	public Ingredient getById(Integer id) {
		return repository.getById(id);
	}

	public Ingredient update(Ingredient ingredient) {
		return repository.save(ingredient);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}
