package com.registration.registration.Exceptions;

import com.registration.registration.Payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHaandling {

//    @ExceptionHandler(value = ResourceNotFound.class)
//    public ResponseEntity<?> handleResourceNotFound(
//            ResourceNotFound ex,
//            WebRequest request
//    ){
//        ErrorDetails err=new ErrorDetails(ex.getMessage(),new Date(),request.getDescription(true));
//        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleAllResources(
            Exception ex
    ){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
