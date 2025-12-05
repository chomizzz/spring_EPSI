// package fr.recette.recette.config;
//
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
//
// import fr.recette.recette.model.Recette;
// import fr.recette.recette.repository.RecetteRepository;
//
// @Component
// public class DatabaseSeeder implements CommandLineRunner {
//
// private final RecetteRepository recetteRepository;
//
// public DatabaseSeeder(RecetteRepository recetteRepository) {
// this.recetteRepository = recetteRepository;
// }
//
// @Override
// public void run(String... args) throws Exception {
// // Vérifier si la table est vide
// if (recetteRepository.count() == 0) {
// Recette r1 = new Recette();
// r1.setId_recette(1L);
// r1.setNom_recette("cookie");
// r1.setIngredient("farine");
//
// Recette r2 = new Recette();
// r2.setNom("Quiche Lorraine");
// r2.setDescription("Quiche classique avec lardons et fromage");
//
// Recette r3 = new Recette();
// r3.setNom("Salade César");
// r3.setDescription("Salade avec poulet, parmesan et croûtons");
//
// // Sauvegarde dans la base
// recetteRepository.save(r1);
// recetteRepository.save(r2);
// recetteRepository.save(r3);
//
// System.out.println("Base de données initialisée avec des recettes !");
// }
// }
// }
