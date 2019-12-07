package sv.edu.ues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sv.edu.ues.config.FakeDataSource;
import sv.edu.ues.tests.ControllerService;

@SpringBootApplication
public class SpringPetClinicApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringPetClinicApplication.class, args);
		ctx.getBean(ControllerService.class).doit();
		FakeDataSource clazz =ctx.getBean(FakeDataSource.class);
		System.out.println("FAKE: "+clazz.pass);
	}

}

