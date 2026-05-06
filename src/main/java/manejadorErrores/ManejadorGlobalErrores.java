package manejadorErrores;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import repositorios.EntidadNoEncontrada;

@ControllerAdvice
public class ManejadorGlobalErrores {
	

    @ExceptionHandler(EntidadNoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFound(EntidadNoEncontrada ex) {
        return ex.getMessage();
    }
    
    @ExceptionHandler(EntidadNoEncontrada.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleNotFound(IllegalArgumentException ex) {
        return ex.getMessage();
    }
    

}
