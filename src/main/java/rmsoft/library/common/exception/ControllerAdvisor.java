package rmsoft.library.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ExceptionResponse> customExceptionHandler(CustomException e) {
        ExceptionResponse response = ExceptionResponse.create(e);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
    }
}

