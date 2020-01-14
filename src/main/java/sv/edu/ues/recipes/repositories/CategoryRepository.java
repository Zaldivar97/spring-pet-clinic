package sv.edu.ues.recipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sv.edu.ues.recipes.model.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{

	Optional<Category> findByDescription(String description);
	
}
