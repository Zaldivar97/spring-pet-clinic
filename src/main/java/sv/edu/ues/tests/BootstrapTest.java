package sv.edu.ues.tests;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.model.Difficulty;
import sv.edu.ues.recipes.model.Ingredient;
import sv.edu.ues.recipes.model.Note;
import sv.edu.ues.recipes.model.Recipe;
import sv.edu.ues.recipes.model.UnitOfMesure;
import sv.edu.ues.recipes.repositories.CategoryRepository;
import sv.edu.ues.recipes.repositories.RecipeRepository;
@Profile({"test","default","dev"})
@Slf4j
@Component
public class BootstrapTest implements ApplicationListener<ContextRefreshedEvent>{
	
	RecipeRepository repository;
	
	CategoryRepository categoryRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("TRIGGERED");
		Optional<Category> cat1 = this.categoryRepository.findByDescription("Italian");

		UnitOfMesure uom = new UnitOfMesure();
		uom.setDescription("uom description");
		UnitOfMesure uom2 = new UnitOfMesure();
		uom.setDescription("uom description");
		Ingredient ing = new Ingredient("ingrediente", new BigDecimal(10), uom);
		Ingredient ing2 = new Ingredient("ingrediente", new BigDecimal(10), uom2);

		Note note = new Note("notas");
		Note note2 = new Note("notas");
		Set<Category> set = Set.of(cat1.orElse(new Category()));
		Recipe other = Recipe.builder()
				.categories(set)
				.cookTime(10)
				.description("Otra una comida italiana")
				.difficulty(Difficulty.HARD)
				.directions("esta es una direccion")
				.build();
		
		other.addIngredients(ing2);
		other.setNote(note);
		Recipe recipe = Recipe.builder()
				.categories(set)
				.cookTime(10)
				.description("Esta es una comida italiana")
				.difficulty(Difficulty.HARD)
				.directions("esta es una direccion")
				.build();
		recipe.addIngredients(ing);
		recipe.setNote(note2);
		
		repository.save(recipe);
		repository.save(other);
	}

	/**
	 * @param repository the repository to set
	 */
	@Autowired
	public void setRepository(RecipeRepository repository) {
		this.repository = repository;
	}

	/**
	 * @param categoryRepository the categoryRepository to set
	 */
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	
	
	
	



}
