package sv.edu.ues.recipes.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleNumberFormat(Exception exception) {
//		log.error("Handling number format exception");
//		return ModelUtil.getModelAndView("YOU MADE A BAD REQUEST, CHECKOUT THE PARAMS", exception.getMessage());
//	}

}
