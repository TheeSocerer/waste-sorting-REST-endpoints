package com.enviro.assessment.grad001.tsheposhiburi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class Enviro365ExceptionHandler extends RuntimeException {

    private final HttpStatus status;

    public Enviro365ExceptionHandler( HttpStatus status,String message) {
        super(message);
        this.status = status;
    }
}
