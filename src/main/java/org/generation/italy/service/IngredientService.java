package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Ingredient;
import org.generation.italy.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class IngredientService {
	@Autowired
	private IngredientRepository repository;
	
	public List<Ingredient> findAllSortByNome() {
		return repository.findAll(Sort.by("nome"));
	}

}
