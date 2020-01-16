package sv.edu.ues.recipes.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.commands.converter.CategoryCommandToCategory;
import sv.edu.ues.recipes.commands.converter.CategorytoCategoryCommand;
import sv.edu.ues.recipes.exceptions.NotFoundException;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.CategoryRepository;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final CategorytoCategoryCommand categorytoCategoryCommand;

	public CategoryServiceImpl(CategoryRepository repository,
			CategoryCommandToCategory categoryCommandToCategory,
			CategorytoCategoryCommand categorytoCategoryCommand) {
		super();
		this.repository = repository;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.categorytoCategoryCommand = categorytoCategoryCommand;
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

//	@Override
//	public CategoryCommand findCommandById(String id) throws Exception {
//		return this.categorytoCategoryCommand.convert(this.findById(id));
//	}

	@Override
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		list = (List<Category>) this.repository.findAll();
		return list;
	}

	@Override
	public Category findById(String id) throws Exception{
	
		if (!id.isBlank()) {
			Optional<Category> catOpt = this.repository.findById(id);
			return catOpt.orElseThrow(() -> new NotFoundException("Category doesn't exist"));
		}
		throw new Exception("Cannot convert the id value, because is not a number");
	}

//	@Override
//	public CategoryCommand saveCategoryCommand(CategoryCommand command) {
//		Category detachedCategory = categoryCommandToCategory.convert(command);
//		Category savedCategory = this.repository.save(detachedCategory);
//		return categorytoCategoryCommand.convert(savedCategory);
//	}

	@Override
	public void saveImage(String id, MultipartFile file) {
		Category category = this.repository.findById(id).get();
		try {
			Byte[] image = new Byte[file.getBytes().length];
			int byteArrayIndex = 0;
			for (byte bait : file.getBytes()) {
				image[byteArrayIndex++] = bait;
			}
			category.setImage(image);
			this.repository.save(category);
			log.info("File saved");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}
