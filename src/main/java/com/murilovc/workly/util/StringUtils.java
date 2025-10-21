package com.murilovc.workly.util;

import java.math.BigDecimal;

public final class StringUtils {

    public static String setText(final Object value) {
        try {
            return switch (value) {
                case null -> null;
                case String s -> !s.trim().isEmpty() ? s.trim() : null;
                case Long l -> String.valueOf(l);
                case Double d -> String.valueOf(d);
                case BigDecimal bd -> bd.toString();
                case Number n -> String.valueOf(n);
                default -> throw new IllegalArgumentException("Text could not be parsed");
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Text could not be parsed");
        }
    }
}
