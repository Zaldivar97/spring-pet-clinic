package sv.edu.ues.recipes.exceptions.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelUtil {
	
	public static ModelAndView getModelAndView(String message,String exceptionMsg) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("error");
		modelView.addObject("msg", message);
		modelView.addObject("ex_message",exceptionMsg);
		return modelView;
	}
}
