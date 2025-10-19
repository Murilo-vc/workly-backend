package com.murilovc.workly.domain.enumeration.field;

import lombok.Getter;

@Getter
public enum UserFieldEnum {
    USERNAME("username"),
    NAME("name"),
    PASSWORD("password"),
    EMAIL("email"),
    PHONE("phone"),
    EXPERIENCE("experience"),
    EDUCATION("education");

    private final String id;

    UserFieldEnum(final String id) {
        this.id = id;
    }
}
