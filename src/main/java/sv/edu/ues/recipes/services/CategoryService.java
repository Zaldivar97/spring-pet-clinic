package sv.edu.ues.recipes.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;

public interface CategoryService {
	List<Category> findAll();
	CategoryCommand saveCategoryCommand(CategoryCommand command);
	Category findById(Long id);
	CategoryCommand findCommandById(Long id);
	void delete(Long id);
	void saveImage(Long id, MultipartFile file);
}
