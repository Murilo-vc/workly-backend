package com.murilovc.workly.util;

import java.math.BigDecimal;

public final class StringUtils {

    public static Integer setInteger(final Object value) {
        try {
            return switch (value) {
                case null -> null;
                case Long l -> BigDecimalUtils.roundToInt(Double.valueOf(l), 0);
                case String s -> {
                    if (s.isBlank()) {
                        yield null;
                    }
                    yield BigDecimalUtils.roundToInt(Double.parseDouble(s.trim()), 0);
                }
                case Double d -> BigDecimalUtils.roundToInt(d, 0);
                case BigDecimal bd -> BigDecimalUtils.roundToInt(bd.doubleValue(), 0);
                case Number n -> BigDecimalUtils.roundToInt((n).doubleValue(), 0);
                default -> throw new IllegalArgumentException("Integer could not be parsed");
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Integer could not be parsed");
        }
    }

    public static Double setNumber(final Object value) {
        try {
            return switch (value) {
                case null -> null;
                case Long l -> BigDecimalUtils.roundToDouble(Double.valueOf(l), 4);
                case String s -> {
                    if (s.isBlank()) {
                        yield null;
                    }
                    yield BigDecimalUtils.roundToDouble(Double.parseDouble(s.trim()), 4);
                }
                case Double d -> BigDecimalUtils.roundToDouble(d, 4);
                case BigDecimal bd -> BigDecimalUtils.roundToDouble(bd.doubleValue(), 4);
                case Number n -> BigDecimalUtils.roundToDouble((n).doubleValue(), 4);
                default -> throw new IllegalArgumentException("Number could not be parsed");
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Number could not be parsed");
        }
    }
}
