package sv.edu.ues.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
//@PropertySource('classpath:')
@Profile({"test","default"})
@Configuration
public class ConfigTest {
	
	@Autowired
	Environment env;
	
	@Value("${fake.name}")
	String user;
	
	@Value("${fake.pasw}")
	String pass;
	
	@Value("${fake.url}")
	String url;

	/*@Bean
	public PropertySourcesPlaceholderConfigurer prop() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
	
	
	@Bean
	public FakeDataSource fake() {
		 FakeDataSource bean = new FakeDataSource();
		 bean.name = user;
		 bean.pass = env.getProperty("PATH");
		 bean.url = url;
		 return bean;
	}
	}


