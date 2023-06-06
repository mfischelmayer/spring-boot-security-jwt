package at.fischelmayer.securitydemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = AuthenticationException.class )
    public ResponseEntity handleIllegalArgumentException( AuthenticationException ex ) {
        return new ResponseEntity( ex.getMessage(), HttpStatus.UNAUTHORIZED );
    }

}
