package sv.edu.ues.recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import sv.edu.ues.recipes.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
