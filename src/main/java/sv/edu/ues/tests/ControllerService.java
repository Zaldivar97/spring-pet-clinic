package sv.edu.ues.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerService {

	Servicio servicio;	
	@Autowired
	public ControllerService(@Qualifier("servicioOtro") Servicio servicioOtro) {//inyecta la implementacion segun su nombre en camel case, p.e. servicioOtro y servicioImpl
		this.servicio=servicioOtro;
	}
	
	public void doit() {
		System.out.println(servicio.hi());
	}
	
}
