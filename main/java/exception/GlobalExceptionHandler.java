package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Rukovanje BookNotFoundException
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Status 404
    @ResponseBody  // Tijelo odgovora
    public String handleBookNotFoundException(BookNotFoundException e) {
        return e.getMessage();  // Vraća poruku koja je postavljena u iznimki
    }

    // Rukovanje InvalidRequestException
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Status 400
    @ResponseBody  // Tijelo odgovora
    public String handleInvalidRequestException(InvalidRequestException e) {
        return e.getMessage();  // Vraća poruku koja je postavljena u iznimki
    }

    // Rukovanje svih ostalih iznimki koje nisu obuhvaćene
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Status 500
    @ResponseBody
    public String handleGenericException(Exception e) {
        return "An unexpected error occurred: " + e.getMessage();  // Vraća generičku poruku za sve ostale iznimke
    }
}
