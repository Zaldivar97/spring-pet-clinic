package sv.edu.ues.recipes.repositories.reactive;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import sv.edu.ues.recipes.model.Category;
@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {
	
	@Autowired
	CategoryReactiveRepository categoryRepository;

	@Before
	public void setUp() throws Exception {
	this.categoryRepository.deleteAll().block();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSave() {

		Category cat = new Category();
		this.categoryRepository.save(cat).block();
		Long count = this.categoryRepository.count().block();
		assertEquals(Long.valueOf(1), count);
	}

}
