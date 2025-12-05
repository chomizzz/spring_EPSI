package fr.recette.recette.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import fr.recette.recette.services.RecetteService;

@Controller
public class RecetteController {

	private final RecetteService recetteService;

	public RecetteController(RecetteService recetteService) {
		this.recetteService = recetteService;
	}

	@GetMapping("/recettes")
	public String getAll(Model model) {
		model.addAttribute("recettes", recetteService.getAllRecettes());
		return "recettes";
	}

}
