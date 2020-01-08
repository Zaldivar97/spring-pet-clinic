package sv.edu.ues.recipes.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.exceptions.GlobalExceptionHandler;
import sv.edu.ues.recipes.exceptions.NotFoundException;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.services.CategoryService;

public class CategoryControllerTest {

	@Mock
	private CategoryService service;

	@Mock
	private Model model;

	@InjectMocks
	private CategoryController controller;

	MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(controller).
				setControllerAdvice(GlobalExceptionHandler.class).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() throws Exception {

		when(service.findAll()).thenReturn(List.of(new Category(1L, "")));
		mvc.perform(get("/categories/")).andExpect(status().isOk()).andExpect(view().name("categories/all"))
				.andExpect(model().attributeExists("categories"));
		verify(service, times(1)).findAll();

	}

	@Test
	public void testFindOne() throws Exception {
		Category cat = new Category(1L, "test");
		when(service.findById(Mockito.anyLong())).thenReturn(cat);
		ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
		this.controller.findOne(model, Mockito.anyLong());
		mvc.perform(get("/categories/1/show")).andExpect(status().isOk())
				.andExpect(view().name("categories/all"))
				.andExpect(model().attributeExists("categories"));
		verify(model, times(1)).addAttribute(Mockito.eq("categories"), captor.capture());

		assertEquals(cat, captor.getValue());
	}
	
	@Test
	public void testControllerAdvice() throws Exception {
		when(service.findById(Mockito.anyLong())).thenThrow(NumberFormatException.class);
		mvc.perform(get("/categories/1/show"))
		.andExpect(status().isBadRequest())
		.andExpect(view().name("error"));
	}
	
	@Test
	public void testExceptionHandler() throws Exception{
		when(service.findById(Mockito.anyLong())).thenThrow(NotFoundException.class);
		mvc.perform(get("/categories/1/show"))
		.andExpect(status().isNotFound())
		.andExpect(view().name("error"));
	}

	@Test
	public void testSave() throws Exception {
		CategoryCommand com = new CategoryCommand();
		com.setId(1L);
		com.setDescription("test");
		mvc.perform(post("/categories/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "1")
				.param("description", "12345678912")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/categories/"));
		verify(service, times(1)).saveCategoryCommand(Mockito.any());
	}

	@Test
	public void testPostImage() throws Exception {
		MockMultipartFile file = new MockMultipartFile("image", "some.txt", "text/plain", "text".getBytes());
		mvc.perform(multipart("/categories/1/image").file(file)).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/categories/1/show"));
		verify(service, times(1)).saveImage(Mockito.anyLong(), Mockito.any());
	}

	@Test
	public void testGetImage() throws Exception {
		String s = "fake image";
		Category cat = new Category(1L, "test cat");
		Byte[] imageBytes = new Byte[s.getBytes().length];
		int i = 0;
		for (byte bait : s.getBytes()) {
			imageBytes[i++] = bait;
		}
		cat.setImage(imageBytes);
		Mockito.when(service.findById(Mockito.anyLong())).thenReturn(cat);

		MockHttpServletResponse response = mvc.perform(get("/categories/1/retrieveImage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		byte[] responseBytes = response.getContentAsByteArray();

		assertEquals(s.getBytes().length, responseBytes.length);
		verify(service, times(1)).findById(Mockito.anyLong());

	}

	@Test
	public void testUpdateCategoryView() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewCategory() {
		fail("Not yet implemented");
	}

}
