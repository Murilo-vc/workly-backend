package com.murilovc.workly.service;

import com.murilovc.workly.domain.dto.LoginDto;
import com.murilovc.workly.domain.payload.LoginPayload;
import com.murilovc.workly.handler.exception.InvalidCredentialsException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public interface AuthService {

    LoginDto login(@NonNull @NotNull final LoginPayload payload) throws InvalidCredentialsException;

    void logout();
}
