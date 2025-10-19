package com.murilovc.workly.handler.exception;

import com.murilovc.workly.domain.constant.ExceptionConstants;

public class InvalidCredentialsException extends UnauthorizedException {

    public InvalidCredentialsException() {
        super(ExceptionConstants.INVALID_CREDENTIALS);
    }
}
