package sv.edu.ues.recipes.commands.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null)
			return null;
		Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		category.setRecipes(source.getRecipes());
		category.setImage(source.getImage());
		return category;
	}

}
