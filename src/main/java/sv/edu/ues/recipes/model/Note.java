package sv.edu.ues.recipes.model;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class Note {
	@Id
	String id;
	private Recipe recipe;
	private String recipeNotes;
	
	public Note(String recipeNotes) {
		super();
		this.recipeNotes = recipeNotes;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
