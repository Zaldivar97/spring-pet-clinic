package sv.edu.ues.recipes.commands;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.edu.ues.recipes.model.Recipe;

@NoArgsConstructor
@Setter
@Getter
@Document
public class CategoryCommand {
	private String id;
	@Size(min = 10,max = 250)
	@NotBlank
	private String description;
	private Set<Recipe> recipes;
	private Byte[] image;

}
