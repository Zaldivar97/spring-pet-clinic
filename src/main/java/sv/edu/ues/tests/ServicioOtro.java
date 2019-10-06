package sv.edu.ues.tests;

import org.springframework.stereotype.Component;

@Component
public class ServicioOtro implements Servicio {

	@Override
	public String hi() {
		return "Servicio otro sysout";
	}

}
