package com.spring.grpc.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNumberException extends RuntimeException {

    public InvalidNumberException(String message) {
        super(message);
    }
}
