package sv.edu.ues.recipes.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import sv.edu.ues.recipes.model.Category;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String>{

}
