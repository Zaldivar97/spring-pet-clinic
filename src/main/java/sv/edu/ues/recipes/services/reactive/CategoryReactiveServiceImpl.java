package sv.edu.ues.recipes.services.reactive;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.commands.converter.CategoryCommandToCategory;
import sv.edu.ues.recipes.commands.converter.CategorytoCategoryCommand;
import sv.edu.ues.recipes.exceptions.NotFoundException;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.reactive.CategoryReactiveRepository;
@Slf4j
@Service
public class CategoryReactiveServiceImpl implements CategoryReactiveService {

	CategoryReactiveRepository categoryRepository;
	CategorytoCategoryCommand command;
	CategoryCommandToCategory catcom;

	public CategoryReactiveServiceImpl(CategoryReactiveRepository categoryRepository, CategorytoCategoryCommand command,
			CategoryCommandToCategory catcom) {
		super();
		this.categoryRepository = categoryRepository;
		this.command = command;
		this.catcom = catcom;
	}

	@Override
	public Mono<CategoryCommand> saveCategoryCommand(CategoryCommand command) {

		return this.categoryRepository.save(this.catcom.convert(command)).map(this.command::convert);
		
	}

	@Override
	public Mono<Category> findById(String id) throws Exception {
		return this.categoryRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("FAIL")));
	
	}

	@Override
	public Mono<CategoryCommand> findCommandById(String id) throws Exception {
		Mono<CategoryCommand> cat = this.categoryRepository.findById(id).map(this.command::convert)
				.doOnError(ex->log.error("error", ex));
		return cat;
	}

	@Override
	public Mono<Void> delete(String id) {
		return this.categoryRepository.deleteById(id);
	}

	@Override
	public Mono<Category> saveImage(String id, MultipartFile file) {
		return this.categoryRepository.findById(id)
		.map(category -> {
			try {
				Byte[] image = new Byte[file.getBytes().length];
				int byteArrayIndex = 0;
				for (byte bait : file.getBytes()) {
					image[byteArrayIndex++] = bait;
				}
				category.setImage(image);
				return category;
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		})
		.publish(categoryMono -> this.categoryRepository.save(categoryMono.block()));
		

	}

	@Override
	public Flux<Category> findAll() {
		return this.categoryRepository.findAll();
	}

}
