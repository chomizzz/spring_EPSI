package fr.recette.recette.data;

import fr.recette.recette.model.Item;
import fr.recette.recette.model.Ingredient;
import fr.recette.recette.model.Recette;
import fr.recette.recette.repository.ItemRepository;
import fr.recette.recette.repository.RecetteRepository;
import fr.recette.recette.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RecetteRepository recetteRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public void run(String... args) throws Exception {
		// Create Items
		Item item1 = new Item(null, "Tomate", "unité");
		Item item2 = new Item(null, "Pâtes", "g");
		Item item3 = new Item(null, "Fromage", "g");
		List<Item> items = itemRepository.saveAll(Arrays.asList(item1, item2,
				item3));
		// Create Recettes
		Recette recette1 = new Recette(null, "Pâtes à la sauce tomate", null);
		Recette recette2 = new Recette(null, "Salade Caprese", null);
		List<Recette> recettes = recetteRepository.saveAll(Arrays.asList(recette1,
				recette2));

		// On associe les item et les recettes
		// Pate à la sauce tomate
		ingredientRepository.save(new Ingredient(null, recette1, item1, "2"));
		ingredientRepository.save(new Ingredient(null, recette1, item2, "200"));

		// Salade Caprese
		ingredientRepository.save(new Ingredient(null, recette2, item1, "2"));
		ingredientRepository.save(new Ingredient(null, recette2, item3, "100"));

		System.out.println("Database seeded successfully!");
	}
}
