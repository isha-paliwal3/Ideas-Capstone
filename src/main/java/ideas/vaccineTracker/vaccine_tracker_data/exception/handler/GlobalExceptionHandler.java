package ideas.vaccineTracker.vaccine_tracker_data.exception.handler;

import ideas.vaccineTracker.vaccine_tracker_data.exception.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        return new ResponseEntity<>("Token expired: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

}
