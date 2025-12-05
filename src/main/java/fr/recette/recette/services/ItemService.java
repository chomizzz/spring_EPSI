
package fr.recette.recette.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.recette.recette.model.Item;
import fr.recette.recette.repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository recetteRepository;

	public ItemService(ItemRepository recetteRepository) {
		this.recetteRepository = recetteRepository;
	}

	public Item create(Item item) {
		return recetteRepository.save(item);
	}

	public void delete(Item item) {
		recetteRepository.delete(item);
	}

	public List<Item> getAllItems() {
		return recetteRepository.findAll();
	}

	public Item getItemById(Long id) {
		return recetteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Item non trouvée"));
	}

}
