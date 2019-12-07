package sv.edu.ues.recipes.commands;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.edu.ues.recipes.model.Recipe;

@NoArgsConstructor
@Setter
@Getter
public class CategoryCommand {
	private Long id;
	private String description;
	private Set<Recipe> recipes;
	private Byte[] image;

}
