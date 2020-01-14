package sv.edu.ues.recipes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document
public class UnitOfMesure {
	@Id
	String id;
	private String description;

	
	
}
