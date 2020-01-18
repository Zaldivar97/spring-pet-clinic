package sv.edu.ues.recipes.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFound(Exception exception, Model model) {
		log.error("Handling not found exception");
		model.addAttribute("msg", "THE ITEM WAS NOT FOUND, CHECK YOUR CODE");
		model.addAttribute("ex_message",exception.getMessage());
		return "error";
	}

}
