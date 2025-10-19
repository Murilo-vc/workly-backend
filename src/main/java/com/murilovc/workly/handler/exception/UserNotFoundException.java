package com.murilovc.workly.handler.exception;

import com.murilovc.workly.domain.constant.ExceptionConstants;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(ExceptionConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
