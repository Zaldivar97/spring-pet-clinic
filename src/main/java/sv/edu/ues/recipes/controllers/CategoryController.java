package sv.edu.ues.recipes.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.services.CategoryService;

@Slf4j
@Controller
@RequestMapping("/categories/")
public class CategoryController {

	private final CategoryService service;

	public CategoryController(CategoryService service) {
		super();
		this.service = service;
	}

	/*AL UTILIZAR DE ESTE MODO LOS MODEL ATTRIBUTES, ES RECOMENDABLE ANOTAR LA CLASE CON
	 *@ControllerAdvice QUE INDICA QUE EL MODELO ES GLOBAL, ES DECIR QUE SERA AGREGADO EN TODOS LOS REQUEST
	 * 
	 * 
	 * AL utilizarlo asi, esto se ejecuta antes de cualquier metodos con requestMapping
	@ModelAttribute
	public void nombre(Model model){
		model.addAttribute("some", "some");
	}
	AL utilizarlo asi, esto se ejecuta antes de cualquier metodos con requestMapping, y lo usamos
	en la vista con el nombre que tiene entre parentesis 
	@ModelAttribute("categories")
	public List<Category> nombre2(){
		return this.service.findAll();
	}
	
	
	*/
	@GetMapping("/{id}/image")
	public String setImage(Model model, @PathVariable("id") Long id) {
		model.addAttribute("category", this.service.findCommandById(id));
		return "categories/showImage";
	}

	@GetMapping("/{id}/retrieveImage")
	public void retrieveImage(@PathVariable("id") Long id, HttpServletResponse response) {
		Category category = this.service.findById(id);
		byte[] image = new byte[category.getImage().length];

		int byteArrayIndex = 0;
		for (Byte bait : category.getImage()) {
			image[byteArrayIndex++] = bait;
		}
		InputStream stream = new ByteArrayInputStream(image);
		try {
			IOUtils.copy(stream, response.getOutputStream());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}

	@PostMapping("/{id}/image")
	public String postImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile file) {
		this.service.saveImage(id, file);
		return "redirect:/categories/" + id + "/show";
	}

	@GetMapping
	public String findAll(Model model) {
		List<Category> list = this.service.findAll();
		model.addAttribute("categories", list);
		return "categories/all";
	}

	@GetMapping("/{id}/show")
	public String findOne(Model model, @PathVariable("id") Long id) {
		Category category = this.service.findById(id);
		model.addAttribute("categories", category);
		return "categories/all";
	}

	@PostMapping
	public String save(@ModelAttribute CategoryCommand cat, BindingResult result, Model model) {
//		if(cat.getDescription().equals("test")) {
//			result.rejectValue("description", "test", "Can't be test");
//			return "categories/new";
//		}

		this.service.saveCategoryCommand(cat);
		return "redirect:/categories/";
	}

	@GetMapping("/{id}/update")
	public String updateCategoryView(@PathVariable("id") Long id, Model model) {
		CategoryCommand com = this.service.findCommandById(id);
		model.addAttribute("cat", com);
		return "categories/new";
	}

	@GetMapping("/{id}/delete")
	// public String delete(@PathVariable("id") Long id, @Valid Categoria)---> si un
	// campo de categoria esta indicado
	// con @Notnull(por ejemplo), la anotacion @Valid verificara que en efecto este
	// campo no sea nulo
	public String delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return "redirect:/categories/";
	}

//	@PutMapping
//	public String updateCategory(@ModelAttribute CategoryCommand cat) {
//		this.service.saveCategoryCommand(cat);
//		return "redirect:/categories/";
//	}

	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("categoryCommand", new CategoryCommand());
		return "categories/new";
	}

}
