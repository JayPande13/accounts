package com.jay.accounts.Exception;

import com.jay.accounts.Dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HashMap<String,String> validationErrorMap = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors(); // This will give me all failed validation in the input data while hitting API

        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validationError = error.getDefaultMessage();
            validationErrorMap.put(fieldName,validationError);
        });

        return new ResponseEntity<>(validationErrorMap,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest request){
        return new ResponseEntity<>( new ErrorResponseDto(request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),
                LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> customerAlreadyExistsExceptionHandler(CustomerAlreadyExistsException customerAlreadyExistsException, WebRequest request){
        return new ResponseEntity<>( new ErrorResponseDto(request.getDescription(false),
                HttpStatus.BAD_REQUEST, customerAlreadyExistsException.getMessage(),
                LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> customerNotFoundExceptionHandler(ResourceNotFoundException customerNotFoundException, WebRequest request){
        return new ResponseEntity<>( new ErrorResponseDto(request.getDescription(false),
                HttpStatus.NOT_FOUND, customerNotFoundException.getMessage(),
                LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

}
