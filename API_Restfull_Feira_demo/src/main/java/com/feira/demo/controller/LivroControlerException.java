package com.feira.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LivroControlerException {

    //tratar as exçeções RuntimeException quando lançadas pelo service
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(e.getMessage());
    }
}
