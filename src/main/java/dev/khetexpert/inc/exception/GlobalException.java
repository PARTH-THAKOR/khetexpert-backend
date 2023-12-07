// GlobalException Exception Class

package dev.khetexpert.inc.exception;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(KhetExpertError.class)
    public ResponseEntity<ApiResponse> khetExpertExceptionHandler(KhetExpertError ex) {
        return new ResponseEntity<>(ApiResponse.builder().success(false).message(ex.getException()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getAllErrors().forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            response.put(fieldName, message);
        }));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> internalServerExceptionHandler(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ApiResponse.builder().success(false).message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

}
