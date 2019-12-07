package sv.edu.ues.recipes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class UnitOfMesure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String description;

	
	
}
