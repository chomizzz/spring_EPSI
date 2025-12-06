## Explication de ce qu'on a fait cette après-midi

Je vais prendre le temps de t'expliquer ici ce qu'on a fait vendredi, j'ai l'impression d'avoir été rapide et avec la fatigue je n'ai pas pris le temps de m'expliquer et j'ai foncé pour finir l'exercice.

### Spring Initialize

- On a donc fait un initializer qui coresspond a un dossier avec tout ce qui faut pour maven se lancer avec les dependance que l'on souhaite (lombok pour raccourcie dans le code (enlever les constructeur, getter et setter), H2 pour la db dans le cache etc..). Ensuite on lance le serveur qui va s'occuper de dl toutes les dépendances. `https://start.spring.io/index.html`

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

-> Ici on doit donc créer le dossier controller, model, services, repository et data.

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
  2. Ensuite on a besoin de repository associé (item, ingredient ou recette), qu'on définit dans un final pour qu'il soit définit qu'une seul fois au moment ou on instancie le service. C'est ce qu'on appelle une injection de dépendance.
  3. Puis on le définie dans le constructeur (c'est ici qu'il est créé lorsqu'on fait une instance de ItemService, IngredientService RecetteService)
  4. Après on fait notre crud normal en appelant les fonction du repository

### Création des repository

- Ici on a un ORM et du coup on a pleins de fonction défini dans JpaRepository, on extends notre Rpository qu'on a créé comme ca on a accès a pleins de fonction. On lui passe le type de notre model, ainsi que le type de son id (`Long` pour tous)
- Notre décorateur @Repository permet a spring de savoir a quoi sert cette class

### Controller

- Ensuite notre controller, on le décord d'un `@Controller` pour que spring le reconnaisse comme un controller. On injecte la dépendance recetteService pour appeler la logique que l'on souhaite, ici on veut récupérer toute les recettes.

### Templating

- on a ajouté tymleaf comme templating. Pour ca on a ajouté dans les dépendances de pom.xml :

```xml
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

```

- ensuite refère toi à l'arborescence pour placer le recettes.html

### Database

- Dans `application.properties` on a fait ceci :
  - `spring.datasource.url=jdbc:h2:file:~/testdb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE` : on dit que l'on créé un fichier avoir la db.
- on dit qu'on peut aller voir la console h2 depuis l'adresse : http://localhost:8080/h2-console/

- Dans le SeedData.java, on va créer nos objets. Comme tu vois on injecte les dépendance avec le @Autowired, c'est une autre façon de faire, moins secure et moins conventionnel. Pour seed une db on peut le faire c'est ok, mais si on voulait faire ca "bien" il me semble qu'il faudrait faire une injection de dépendance comme on a fait dans le controller et les services.
- On crée des item (grace aux constructeurs) dans le model. On les save. On créé les recette et on les save. Puis on créé les ingredients. Tu peux voir deux façon de faire: 
  - pour les recettes et items on a créé les objet, puis on les saveAll, la fonction accepete un List d'Item ou de Recette (c'est ce qu'on a déterminé dans le repository avec le JPA. 
  - Pour les ingredient créé l'objet directement dans la fonction de save, ca fait du 1 line, ca fait moins de lignes,c'est à l'appréciation de chacun pour la méthode.  


