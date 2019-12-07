package sv.edu.ues.recipes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.NoArgsConstructor;
@NoArgsConstructor
@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@OneToOne
	private Recipe recipe;
	private String recipeNotes;
	
	public Note(String recipeNotes) {
		super();
		this.recipeNotes = recipeNotes;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return recipeNotes;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.recipeNotes = note;
	}
	/**
	 * @return the recipe
	 */
	public Recipe getRecipe() {
		return recipe;
	}
	/**
	 * @param recipe the recipe to set
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
}
