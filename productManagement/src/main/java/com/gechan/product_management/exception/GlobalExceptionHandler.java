package com.gechan.product_management.exception;

import com.gechan.product_management.domain.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String extractField(Path path) {
        String[] splittedArray = path.toString().split("[.]");
        int lastIndex = splittedArray.length - 1;
        return splittedArray[lastIndex];
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolatedException(
            ConstraintViolationException e
    ) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> errors = constraintViolations.stream()
                .map(constraintViolation ->
                        extractField(constraintViolation.getPropertyPath()) + ", " +
                                constraintViolation.getMessage())
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(
                        fieldError -> fieldError.getField() + ", " + fieldError.getDefaultMessage()
                ).toList();

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(
            EntityNotFoundException e
    ) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
