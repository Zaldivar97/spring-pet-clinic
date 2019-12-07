package sv.edu.ues.recipes.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String description;
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;
	@Lob
	private Byte[] image;
	
	public Category(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	
	
}
