package sv.edu.ues.config;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import reactor.core.publisher.Flux;
import sv.edu.ues.recipes.model.Category;
import sv.edu.ues.recipes.services.reactive.CategoryReactiveService;

public class ConfigTestTest {
	
	WebTestClient client;
	
	@Mock
	CategoryReactiveService service;

	@Before
	public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
	ConfigTest config = new ConfigTest();
	RouterFunction<?> router = config.router(service);
	client = WebTestClient.bindToRouterFunction(router).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testRouter() {
		when(service.findAll()).thenReturn(Flux.just(new Category("abc", "testt-cat")));
		client.get().uri("/api/cat")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Category.class);
	}

}
