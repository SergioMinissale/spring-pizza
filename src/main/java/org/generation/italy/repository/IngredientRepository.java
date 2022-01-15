package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Ingredient;
import org.generation.italy.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	List<Ingredient> findByNameContainingIgnoreCaseOrderByName(String keyword);

}
