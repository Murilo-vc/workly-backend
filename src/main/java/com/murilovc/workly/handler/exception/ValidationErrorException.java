package com.murilovc.workly.handler.exception;

import com.murilovc.workly.domain.constant.ExceptionConstants;
import com.murilovc.workly.domain.dto.FieldErrorDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationErrorException extends BaseException {

    private final List<FieldErrorDto> details;

    public ValidationErrorException(final List<FieldErrorDto> details) {
        super(ExceptionConstants.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        this.details = details;
    }
}
