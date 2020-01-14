package sv.edu.ues.recipes.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Category {
	@Id
	private String id;
	private String description;
	@DBRef
	private Set<Recipe> recipes;
	private Byte[] image;
	
	public Category(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	
	
}
