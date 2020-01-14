package sv.edu.ues.recipes.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;

public interface CategoryService {
	List<Category> findAll();
	CategoryCommand saveCategoryCommand(CategoryCommand command);
	Category findById(String id) throws Exception;
	CategoryCommand findCommandById(String id) throws Exception;
	void delete(String id);
	void saveImage(String id, MultipartFile file);
}
