package sv.edu.ues.recipes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import sv.edu.ues.recipes.model.Recipe;
import sv.edu.ues.recipes.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	

	RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		this.recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {
		Optional<Recipe> recipe = this.recipeRepository.findById(id);
		return recipe.orElse(new Recipe(0L, "RECIPE NOT FOUND"));
	}

}
