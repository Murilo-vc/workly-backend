package com.murilovc.workly.util;

import com.murilovc.workly.config.security.SecurityConfig;

import java.util.Arrays;

public final class PathUtils {

    public static boolean isPublic(String path) {
        return Arrays.stream(SecurityConfig.WHITELIST_PATTERNS).anyMatch(path::startsWith);
    }
}
