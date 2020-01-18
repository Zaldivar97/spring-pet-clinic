package sv.edu.ues.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
//@PropertySource('classpath:')
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.services.reactive.CategoryReactiveService;
@Profile({"dev","default"})
@Configuration
public class ConfigTest {
	
	@Bean
	RouterFunction<?> router(CategoryReactiveService categoryService){
		return RouterFunctions.route(RequestPredicates.GET("/api/cat"),
									request -> ServerResponse
										.ok()
										.contentType(MediaType.APPLICATION_JSON)
										.body(categoryService.findAll(), Category.class));
	}
	
//	@Autowired
//	Environment env;
//	
//	@Value("${fake.name}")
//	String user;
//	
//	@Value("${fake.pasw}")
//	String pass;
//	
//	@Value("${fake.url}")
//	String url;

	/*@Bean
	public PropertySourcesPlaceholderConfigurer prop() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
	
	
//	@Bean
//	public FakeDataSource fake() {
//		 FakeDataSource bean = new FakeDataSource();
//		 bean.name = user;
//		 bean.pass = env.getProperty("PATH");
//		 bean.url = url;
//		 return bean;
//	}
	}


