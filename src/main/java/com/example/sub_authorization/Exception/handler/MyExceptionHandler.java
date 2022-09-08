package com.example.sub_authorization.Exception.handler;

import com.example.sub_authorization.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value={UserNotFoundException.class})
    public String handlerUserNotFound(UserNotFoundException e){
        System.out.println("If UserNotFoundException throws, it will be intercepted by this handler");
        e.printStackTrace();
        return "redirect:http://localhost:8081/user/toLogin";
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<String> handlerException(Exception e){
        return new ResponseEntity("Unknown Exception Happened", HttpStatus.NOT_FOUND);
    }
}
