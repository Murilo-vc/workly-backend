package com.murilovc.workly.handler.exception;

import com.murilovc.workly.domain.constant.ExceptionConstants;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        super(ExceptionConstants.FORBIDDEN, HttpStatus.FORBIDDEN);
    }
}
