package sv.edu.ues.recipes.services;

import java.util.Set;

import sv.edu.ues.recipes.model.Recipe;


public interface RecipeService {

	Set<Recipe> getRecipes();
	Recipe findById(String id);
	
}
