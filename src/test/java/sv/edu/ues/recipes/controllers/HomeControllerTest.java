package sv.edu.ues.recipes.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.CategoryRepository;

public class HomeControllerTest {
	
	HomeController controller;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		controller = new HomeController(categoryRepository);
	}
	
	
	@Test
	public void mockMvcTest() throws Exception {
      //con este metodo deja de ser unit test y se convierte en test de integracion porque carga el spring context
//  	MockMvcBuilders.webAppContextSetup(context)
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void testHome() {
		
		when(categoryRepository.findByDescription("some")).thenReturn(Optional.of(new Category()));
		String value = controller.home();
		verify(categoryRepository, times(1)).findByDescription(Mockito.anyString());
		assertTrue(value.equals(controller.home()));
	
//		fail("Not yet implemented");
	}

}
