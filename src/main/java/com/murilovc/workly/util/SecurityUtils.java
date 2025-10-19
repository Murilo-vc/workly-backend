package com.murilovc.workly.util;

import com.murilovc.workly.security.user.AppUserDetails;
import com.murilovc.workly.security.user.AppUserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public final class SecurityUtils {

    public static AppUserDetails getCurrentUser() {
        return getContext()
            .map(authentication -> {
                final Object principal = authentication.getPrincipal();

                if (principal instanceof UserDetails) {
                    return ((AppUserDetails) principal);
                }

                return null;
            })
            .orElseGet(() -> new AppUserDetailsImpl("ANONYMOUS"));
    }

    private static Optional<Authentication> getContext() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}
