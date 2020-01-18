package sv.edu.ues.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import sv.edu.ues.recipes.commands.CategoryCommand;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.services.reactive.CategoryReactiveService;

@Slf4j
@Controller
@RequestMapping("/categories/")
public class CategoryController {

	private final CategoryReactiveService service;
	private WebDataBinder dataBinder;
	
	public CategoryController(CategoryReactiveService service) {
		super();
		this.service = service;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		this.dataBinder = dataBinder;
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
	public String setImage(Model model, @PathVariable("id") String id) throws Exception {
		model.addAttribute("category", this.service.findCommandById(id));
		return "categories/showImage";
	}

//	@GetMapping("/{id}/retrieveImage")
//	public void retrieveImage(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
//		Category category = this.service.findById(id).block();
//		if(category!=null)log.info("no es NULO");
//		byte[] image = new byte[category.getImage().length];
//
//		int byteArrayIndex = 0;
//		for (Byte bait : category.getImage()) {
//			image[byteArrayIndex++] = bait;
//		}
//		InputStream stream = new ByteArrayInputStream(image);
//		try {
//			IOUtils.copy(stream, response.getOutputStream());
//		} catch (IOException e) {
//			log.info(e.getMessage());
//		}
//	}

	@PostMapping("/{id}/image")
	public String postImage(@PathVariable("id") String id, @RequestParam("image") MultipartFile file) {
		this.service.saveImage(id, file).subscribe();
		return "redirect:/categories/" + id + "/show";
	}

	@GetMapping
	public String findAll(Model model) {
		log.info("ENTRA");
		model.addAttribute("categories", this.service.findAll().collectList());
		return "categories/all";
	}

	@GetMapping("/{id}/show")
	public String findOne(Model model, @PathVariable("id") String id) throws Exception {
		Mono<Category> category = this.service.findById(id);
		model.addAttribute("categories", category);
		return "categories/all";
	}

	@PostMapping
	public String save(@ModelAttribute("cat") CategoryCommand cat, Model model) {
//		if(cat.getDescription().equals("test")) {
//			result.rejectValue("description", "test", "Can't be test");
//			return "categories/new";
//		}
		dataBinder.validate();
		BindingResult result = dataBinder.getBindingResult();
		if(result.hasErrors())
			return "categories/new";

		this.service.saveCategoryCommand(cat).subscribe();
		return "redirect:/categories/";
	}

	@GetMapping("/{id}/update")
	public String updateCategoryView(@PathVariable("id") String id, Model model) throws Exception {
		this.service.findCommandById(id).subscribe(categorycommand -> model.addAttribute("cat", categorycommand));
		return "categories/new";
	}

	@GetMapping("/{id}/delete")
	// public String delete(@PathVariable("id") Long id, @Valid Categoria)---> si un
	// campo de categoria esta indicado
	// con @Notnull(por ejemplo), la anotacion @Valid verificara que en efecto este
	// campo no sea nulo
	public String delete(@PathVariable("id") String id) {
		this.service.delete(id).subscribe();
		return "redirect:/categories/";
	}

//	@PutMapping
//	public String updateCategory(@ModelAttribute CategoryCommand cat) {
//		this.service.saveCategoryCommand(cat);
//		return "redirect:/categories/";
//	}

	@GetMapping("/new")
	public String newCategory(Model model) {
//		this.service.findAll().forEach(item -> System.out.println(item.getId()+"-"+item.getDescription()));
		model.addAttribute("cat", new CategoryCommand());
		return "categories/new";
	}
	
	
	

	
	
	
	
	
	
	
	
	

}
