package sv.edu.ues.recipes.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sv.edu.ues.recipes.model.Recipe;
import sv.edu.ues.recipes.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	
	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	RecipeServiceImpl recipeService;

	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testGetRecipes() throws Exception{
		Mockito.when(recipeRepository.findAll()).thenReturn(List.of(new Recipe(), new Recipe()));
//		ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
		assertTrue(this.recipeService.getRecipes().size()==2);
		Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
		//		fail("Not yet implemented");
	}
//	
//	@Test
//	public void testFindOne() {
//		Mockito.when(recipeRepository.findById(Mockito.any())).thenReturn(Optional.of(new Recipe(1L, "test")));
//		assertEquals("test", recipeService.findById(1L).getDescription());
//		Mockito.verify(recipeRepository,times(1)).findById(Mockito.any());
//		
//	}

}
