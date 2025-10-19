package com.murilovc.workly.service;

import com.murilovc.workly.domain.dto.UserDto;
import com.murilovc.workly.domain.payload.UserCreatePayload;
import com.murilovc.workly.domain.payload.UserUpdatePayload;
import com.murilovc.workly.handler.exception.ForbiddenException;
import com.murilovc.workly.handler.exception.UserNotFoundException;
import com.murilovc.workly.handler.exception.UsernameAlreadyExistsException;
import com.murilovc.workly.handler.exception.ValidationErrorException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public interface UserService {

    UserDto getUserById(@NonNull @NotNull final Long id) throws UserNotFoundException, ForbiddenException;

    void create(@NonNull @NotNull final UserCreatePayload payload) throws UsernameAlreadyExistsException, ValidationErrorException;

    void update(@NonNull @NotNull final Long id,
                @NotNull @NotNull final UserUpdatePayload payload) throws ForbiddenException, UserNotFoundException, ValidationErrorException;

    void delete(@NonNull @NotNull final Long id) throws ForbiddenException, UserNotFoundException;
}
