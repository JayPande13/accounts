package com.jay.accounts.Exception;

import com.jay.accounts.Dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

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
