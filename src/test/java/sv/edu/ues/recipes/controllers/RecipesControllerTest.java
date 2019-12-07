package sv.edu.ues.recipes.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sv.edu.ues.recipes.model.Recipe;
import sv.edu.ues.recipes.services.RecipeService;

public class RecipesControllerTest {
	
	@Mock
	RecipeService service;
	
	@InjectMocks
	RecipesController controller;
	
	MockMvc mvc;
	
	Set<Recipe> set;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		Recipe recipe1 = Recipe.builder().cookTime(1).build();
		Recipe recipe2 = Recipe.builder().build();
		set = Set.of(recipe1,recipe2); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() throws Exception {
		when(service.getRecipes()).thenReturn(set);
		mvc.perform(get("/recipes/all"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipes"))
		.andExpect(model().attribute("recipes", set));
		//fail("Not yet implemented");
	}

	@Test
	public void testFindOne() throws Exception {
		Recipe recipe = new Recipe(1L, "test");
		when(service.findById(Mockito.any())).thenReturn(recipe);
		mvc.perform(get("/recipes/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipes"))
		.andExpect(model().attribute("recipes", recipe));
//		fail("Not yet implemented");
	}

}












