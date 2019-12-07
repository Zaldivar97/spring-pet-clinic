package sv.edu.ues.recipes.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sv.edu.ues.recipes.model.Recipe;
import sv.edu.ues.recipes.services.RecipeService;

@RequestMapping("/recipes")
@Controller
public class RecipesController {

	private RecipeService service;

	public RecipesController(RecipeService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	protected String findAll(Model model) {
		Set<Recipe> recipes = this.service.getRecipes();
		model.addAttribute("recipes", recipes);
		return "recipes";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	protected String findOne(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipes",this.service.findById(id));
		return "recipes";
	}
	
	
	
}
