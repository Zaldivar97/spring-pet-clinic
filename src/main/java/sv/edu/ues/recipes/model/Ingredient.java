package sv.edu.ues.recipes.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class Ingredient {

	@Id
	String id;
	private String description;
	private BigDecimal amount;
	@DBRef
	private UnitOfMesure unitOfMesure;
	private Recipe recipe;

	public Ingredient(String description, BigDecimal amount, UnitOfMesure unitOfMesure) {
		super();
		this.description = description;
		this.amount = amount;
		this.unitOfMesure = unitOfMesure;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMesure unitOfMesure, Recipe recipe) {
		super();
		this.description = description;
		this.amount = amount;
		this.unitOfMesure = unitOfMesure;
		this.recipe = recipe;
	}

	/**
	 * @return the unitOfMesure
	 */
	public UnitOfMesure getUnitOfMesure() {
		return unitOfMesure;
	}

	/**
	 * @param unitOfMesure the unitOfMesure to set
	 */
	public void setUnitOfMesure(UnitOfMesure unitOfMesure) {
		this.unitOfMesure = unitOfMesure;
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
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
