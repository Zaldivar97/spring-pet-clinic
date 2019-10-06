package sv.edu.ues.tests;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//primary indica el bean principal o por defecto que se va inyectar si hay mas de uno, si se utiliza @Qualifier,
//el bean indicado mediante qualifier lo sobreescribe
public class ServicioImpl implements Servicio {

	@Override
	public String hi() {
		return "Servicio impl sysout";
	}

}
