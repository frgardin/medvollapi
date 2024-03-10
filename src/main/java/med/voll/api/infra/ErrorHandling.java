package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Error() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400Error(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(e.getFieldErrors()
                        .stream()
                        .map(ErrorValidDTO::new)
                        .toList());
    }

    private record ErrorValidDTO(String field, String message) {

        public ErrorValidDTO(FieldError er) {
            this(er.getField(), er.getDefaultMessage());
        }
    }
}
