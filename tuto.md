## Explication de ce qu'on a fait cette après-midi

Je vais prendre le temps de t'expliquer ici ce qu'on a fait aujourd'hui, j'ai l'impression d'avoir été rapide et avec la fatigue j'ai l'impression de ne pas avoir pris le temps de t'expliquer :

### Spring Initialize

- On a donc fait un initializer qui coresspond a un dossier avec tout ce qui faut pour maven se lancer avec les dependance que l'on souhaite (lombok pour raccourcie dans le code (enlever les constructeur, getter et setter), H2 pour la db dans le cache etc..). Ensuite on lance le serveur qui va s'occuper de dl toutes les dépendances.

### Création des dossiers

- Ensuite on doit créer les dossier pour organiser notre code :

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── fr
│   │   │       └── recette
│   │   │           └── recette
│   │   │               ├── config
│   │   │               │   └── DatabaseSeeder.java
│   │   │               ├── controller
│   │   │               │   ├── HelloController.java
│   │   │               │   └── RecetteController.java
│   │   │               ├── data
│   │   │               │   └── SeedData.java
│   │   │               ├── model
│   │   │               │   ├── Ingredient.java
│   │   │               │   ├── Item.java
│   │   │               │   └── Recette.java
│   │   │               ├── RecetteApplication.java
│   │   │               ├── repository
│   │   │               │   ├── IngredientRepository.java
│   │   │               │   ├── ItemRepository.java
│   │   │               │   └── RecetteRepository.java
│   │   │               └── services
│   │   │                   ├── IngredientService.java
│   │   │                   ├── ItemService.java
│   │   │                   └── RecetteService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data
│   │       ├── static
│   │       └── templates
│   │           └── recettes.html
│   └── test
│       └── java
│           └── fr
│               └── recette
│                   └── recette
│                       └── RecetteApplicationTests.java
```

-> Ici on doit donc créer le dossier controller, model, services et repository.

### Création des models

- On doit ensuite définir notre BO, nos modeles : Recette, Ingredient et Item. Recette Ingredient est la table de liaison qui définie le lien entre une recette et ces ingrédients.
- Dans chacun de nos model on a :
  1. des décorateurs au dessus chaque classe :
  - @Data : permet de générer automatiquement les getter et setter
  - @AllArgsConstructor : génère le constructeur avec les tout les attributs
  - @NoArgsConstructor : génère un constructeur vide
  - @Entity : dit a l'ORM que c'est une "entité", donc un modèle (ca lui dit quoi faire pour la db
  2. déclaration des attributs : décorateur `@Id` pour dire que c'est ce qui va servir d'id dans la db, @GeneratedValue (c'est pour que la db choississent elle même l'id)

### Creation des services

- Pour être plus claire que tal, notre service va appeler ntore répository. Pourquoi séparer en deux couche : quand on fait que du crud c'est simple, notre service fait une ligne car il appelle la fonction definie dans le repository. Ca arrive souvent dans un application avancé qu'on ai besoin de faire des opérations avant d'appeler le repository.
- pour chacun de nos services on a :
  1. un décorateur au dessus de la définition de la class : @Service (ca sert a spring pour savoir a quoi sert la class)
  2. Ensuite on a besoin de repository associé (item, ingredient ou recette), qu'on définit dans un final pour qu'il soit définit qu'une seul fois au moment ou on instancie le service
  3. Puis on le définie dans le constructeur (c'est ici qu'il est créé lorsqu'on fait une instance de ItemService, IngredientService RecetteService)
  4. Après on fait notre crud normal en appelant les fonction du repository

### Création des repository

- Ici on a un ORM et du coup on a pleins de fonction défini dans JpaRepository, on extends notre Rpository qu'on a créé comme ca on a accès a pleins de fonction. On lui passe le type de notre model, ainsi que le type de son id (`Long` pour tous)
- Notre décorateur @Repository permet a spring de savoir a quoi sert cette class

### Database
- Dans application.properties on a fait ceci : 
    - `spring.datasource.url=jdbc:h2:file:~/testdb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE` : on dit que l'on créé un fichier avoir la db.
- on dit qu'on peut aller voir la console h2 depuis l'adresse : http://localhost:8080/h2-console/
- on feed la db dans le dossier que tu as vu plus haut dans l'arborescence (ca vaut le coup que tu regarde comment on créé des objets, attention il fait des boucle bizarre je pendrais le temps de faire un truc plus normal

### Templating
- on a ajouté tymleaf comme templating. Pour ca on a ajouté dans les dépenance de pom.xml :
```xml
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

```
- ensuite refère toi à l'arborescence ou se met la vue. 


*Je finirais plus tard pour le controller je dois bouger bisous bonne semaine*

