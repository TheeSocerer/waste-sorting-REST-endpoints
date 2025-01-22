package com.enviro.assessment.grad001.tsheposhiburi.exception;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;


@AllArgsConstructor
public class Enviro365ExceptionHandler extends RuntimeException {

    private HttpStatus status;
    private String message;

    public Enviro365ExceptionHandler(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;}
}
