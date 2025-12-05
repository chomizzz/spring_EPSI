
package fr.recette.recette.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.recette.recette.model.Recette;
import fr.recette.recette.repository.RecetteRepository;

@Service
public class RecetteService {

	private final RecetteRepository recetteRepository;

	public RecetteService(RecetteRepository recetteRepository) {
		this.recetteRepository = recetteRepository;
	}

	public Recette create(Recette recette) {
		return recetteRepository.save(recette);
	}

	public void delete(Recette recette) {
		recetteRepository.delete(recette);
	}

	public List<Recette> getAllRecettes() {
		return recetteRepository.findAll();
	}

	public Recette getRecetteById(Long id) {
		return recetteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Recette non trouvée"));
	}

}
