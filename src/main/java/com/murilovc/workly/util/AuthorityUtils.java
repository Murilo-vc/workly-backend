package com.murilovc.workly.util;

import com.murilovc.workly.domain.constant.UserRoleConstants;
import com.murilovc.workly.domain.enumeration.RoleKeyEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class AuthorityUtils {

    public static Set<GrantedAuthority> createAuthorities(final RoleKeyEnum role) {
        if (role == RoleKeyEnum.COMPANY) {
            return Set.of(new SimpleGrantedAuthority(UserRoleConstants.COMPANY));
        }

        return Set.of(new SimpleGrantedAuthority(UserRoleConstants.USER));
    }
}
