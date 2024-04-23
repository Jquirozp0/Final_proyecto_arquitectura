package com.example.RegistraduriaFinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResurceNotFoundException extends RuntimeException{
    public ResurceNotFoundException(String s) {
    }
}
