package sv.edu.ues.recipes.commands;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.edu.ues.recipes.model.Recipe;

@NoArgsConstructor
@Setter
@Getter
public class CategoryCommand {
	private Long id;
	@Size(min = 10,max = 250)
	@NotBlank
	private String description;
	private Set<Recipe> recipes;
	private Byte[] image;

}
