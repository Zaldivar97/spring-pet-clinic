package sv.edu.ues.recipes.services.reactive;

import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;

public interface CategoryReactiveService {
	
	Flux<Category> findAll();
	Mono<CategoryCommand> saveCategoryCommand(CategoryCommand command);
	Mono<Category> findById(String id) throws Exception;
	Mono<CategoryCommand> findCommandById(String id) throws Exception;
	Mono<Void> delete(String id);
	Mono<Void> saveImage(String id, MultipartFile file);

}
