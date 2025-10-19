package com.murilovc.workly.handler.exception;

import com.murilovc.workly.domain.constant.ExceptionConstants;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends BaseException {

    public UsernameAlreadyExistsException() {
        super(ExceptionConstants.USERNAME_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }
}
