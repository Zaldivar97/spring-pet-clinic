package sv.edu.ues.recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.recipes.exceptions.util.ModelUtil;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ModelAndView handleNumberFormat(Exception exception) {
		log.error("Handling number format exception");
		return ModelUtil.getModelAndView("YOU MADE A BAD REQUEST, CHECKOUT THE PARAMS", exception.getMessage());
	}

}
