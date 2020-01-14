package sv.edu.ues.recipes.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor()
@Builder
@Document
public class Recipe {

	@Id
	private String id;
	private String source;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private String directions;
	private Note note;
	private Difficulty difficulty;
	@Builder.Default
	private Set<Ingredient> ingredients = new HashSet<>();
	@DBRef
	private Set<Category> categories;
	
	public Recipe(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	/**
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * @return the ingredients
	 */
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public Recipe addIngredients(Ingredient ingredient) {
		this.ingredients.add(ingredient);
//		ingredient.setRecipe(this);
		return this;
	}
	
	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the prepTime
	 */
	public Integer getPrepTime() {
		return prepTime;
	}
	/**
	 * @param prepTime the prepTime to set
	 */
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	/**
	 * @return the cookTime
	 */
	public Integer getCookTime() {
		return cookTime;
	}
	/**
	 * @param cookTime the cookTime to set
	 */
	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}
	/**
	 * @return the directions
	 */
	public String getDirections() {
		return directions;
	}
	/**
	 * @param directions the directions to set
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	/**
	 * @return the note
	 */
	public Note getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(Note note) {
		this.note = note;
//		this.note.setRecipe(this);
	}

	
}
