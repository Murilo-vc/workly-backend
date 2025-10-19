package com.murilovc.workly.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.murilovc.workly.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements BaseDto {

    private final String name;

    private final String username;

    private final String email;

    private final String phone;

    private final String experience;

    private final String education;

    @Builder
    public UserDto(final String name,
                   final String username,
                   final String email,
                   final String phone,
                   final String experience,
                   final String education) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.experience = experience;
        this.education = education;
    }

    public static UserDto toDto(final User user) {
        return UserDto.builder()
            .name(user.getName())
            .username(user.getUsername())
            .email(user.getEmail())
            .phone(user.getPhone())
            .experience(user.getExperience())
            .education(user.getEducation())
            .build();
    }
}
