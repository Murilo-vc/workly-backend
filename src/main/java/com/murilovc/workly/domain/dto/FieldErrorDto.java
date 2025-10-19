package com.murilovc.workly.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorDto implements BaseDto {

    private final String field;

    private final String error;

    @Builder
    protected FieldErrorDto(final String field,
                            final String error) {
        this.field = field;
        this.error = error;
    }

    public static FieldErrorDto toDto(final String field,
                                      final String error) {
        return FieldErrorDto.builder()
            .field(field)
            .error(error)
            .build();
    }
}
