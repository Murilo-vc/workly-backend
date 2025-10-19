package com.murilovc.workly.handler.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException() {
        super("Invalid token", HttpStatus.UNAUTHORIZED);
    }
}
