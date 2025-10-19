package com.murilovc.workly.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdatePayload implements BasePayload {

    private String name;

    private String password;

    private String email;

    private String phone;

    private String experience;

    private String education;
}
