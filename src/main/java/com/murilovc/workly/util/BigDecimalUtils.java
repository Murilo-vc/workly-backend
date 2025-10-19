package com.murilovc.workly.util;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimalUtils {

    public static int roundToInt(double value, @Min(0) int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        return bd.intValue();
    }

    public static Double roundToDouble(double value, @Min(0) int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}
