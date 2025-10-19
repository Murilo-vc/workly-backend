package com.murilovc.workly.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto implements BaseDto {

    private final String token;

    private final int expires_in;

    protected LoginDto(final String token,
                       final int expires_in) {
        this.token = token;
        this.expires_in = expires_in;
    }

    public static LoginDto toDto(@NonNull @NotNull final String token,
                                 @NonNull @NotNull final Integer expires_in) {
        return LoginDto.builder()
            .token(token)
            .expires_in(expires_in)
            .build();
    }
}
