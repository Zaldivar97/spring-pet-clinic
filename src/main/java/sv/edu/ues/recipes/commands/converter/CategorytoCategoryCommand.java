package sv.edu.ues.recipes.commands.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;
@Component
public class CategorytoCategoryCommand implements Converter<Category, CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if (source == null)
			return null;
		CategoryCommand command = new CategoryCommand();
		command.setId(source.getId());
		command.setDescription(source.getDescription());
		command.setRecipes(source.getRecipes());
		command.setImage(source.getImage());
		return command;
	}

}
