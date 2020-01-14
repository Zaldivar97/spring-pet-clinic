package sv.edu.ues.recipes.services.reactive;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.commands.converter.CategoryCommandToCategory;
import sv.edu.ues.recipes.commands.converter.CategorytoCategoryCommand;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.reactive.CategoryReactiveRepository;

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

		Category category = this.categoryRepository.save(this.catcom.convert(command)).block();

		return Mono.just(this.command.convert(category));
	}

	@Override
	public Mono<Category> findById(String id) throws Exception {
		return this.categoryRepository.findById(id);
	
	}

	@Override
	public Mono<CategoryCommand> findCommandById(String id) throws Exception {
		Category cat = this.categoryRepository.findById(id).block();
		return Mono.just(this.command.convert(cat));
	}

	@Override
	public Mono<Void> delete(String id) {
		this.categoryRepository.deleteById(id).block();
		return Mono.empty();
	}

	@Override
	public Mono<Void> saveImage(String id, MultipartFile file) {
		Mono<Category> mono = this.categoryRepository.findById(id)
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
		}).publish(cat -> this.categoryRepository.save(cat.block()));
		mono.block();
		return Mono.empty();

	}

	@Override
	public Flux<CategoryCommand> findAll() {
		return this.categoryRepository.findAll().map(command::convert);
	}

}
