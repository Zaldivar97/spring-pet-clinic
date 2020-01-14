package sv.edu.ues.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.repositories.CategoryRepository;
import sv.edu.ues.recipes.repositories.RecipeRepository;
import sv.edu.ues.recipes.repositories.reactive.CategoryReactiveRepository;
@Profile({"test","default","dev"})
@Slf4j
@Component
public class BootstrapTest implements ApplicationListener<ContextRefreshedEvent>{
	
	RecipeRepository repository;
	
	@Autowired
	CategoryReactiveRepository reactive;
	
	CategoryRepository categoryRepository;
	
	  private void loadCategories(){
	        Category cat1 = new Category();
	        cat1.setDescription("American");
	        categoryRepository.save(cat1);

	        Category cat2 = new Category();
	        cat2.setDescription("Italian");
	        categoryRepository.save(cat2);

	        Category cat3 = new Category();
	        cat3.setDescription("Mexican");
	        categoryRepository.save(cat3);

	        Category cat4 = new Category();
	        cat4.setDescription("Fast Food");
	        categoryRepository.save(cat4);
	    }
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("TRIGGERED");
		loadCategories();
		log.info("COUNT: "+reactive.count().block().toString());

	}

	/**
	 * @param repository the repository to set
	 */
	@Autowired
	public void setRepository(RecipeRepository repository) {
		this.repository = repository;
	}

	/**
	 * @param categoryRepository the categoryRepository to set
	 */
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	
	
	
	



}
