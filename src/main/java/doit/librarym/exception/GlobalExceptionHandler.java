package doit.librarym.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException userException) {
        return ResponseEntity.ok(userException.getErrorMessage());
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> handleBookException(BookException bookException) {
        return ResponseEntity.ok(bookException.getErrorMessage());
    }
}
