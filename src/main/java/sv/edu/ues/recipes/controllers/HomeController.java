package sv.edu.ues.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.CategoryRepository;

@Controller
public class HomeController {

	private CategoryRepository categoryRepository;
	

	public HomeController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@RequestMapping({"/","/index"})
	public String home() {
		this.categoryRepository.findByDescription("Italian").ifPresent(this::process);
		return "index";
	}
	
	private void process(Category value) {
		System.out.println("Este es el id: ".concat(value.getId().toString()));
	}
	
	
}
