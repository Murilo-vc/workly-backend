package com.murilovc.workly.domain.enumeration;

import lombok.Getter;

@Getter
public enum RoleKeyEnum {
    COMPANY("company"),
    USER("user");

    private final String id;

    RoleKeyEnum(final String id) {
        this.id = id;
    }
}
