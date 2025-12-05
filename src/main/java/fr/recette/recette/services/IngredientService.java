
package fr.recette.recette.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.recette.recette.model.Ingredient;
import fr.recette.recette.repository.IngredientRepository;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public Ingredient create(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	public void delete(Ingredient ingredient) {
		ingredientRepository.delete(ingredient);
	}

	public List<Ingredient> getAllIngredients() {
		return ingredientRepository.findAll();
	}

	public Ingredient getIngredientById(Long id) {
		return ingredientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ingredient non trouvée"));
	}

}
