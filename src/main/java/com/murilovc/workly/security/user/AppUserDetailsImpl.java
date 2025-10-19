package com.murilovc.workly.security.user;

import com.murilovc.workly.domain.entity.User;
import com.murilovc.workly.domain.enumeration.RoleKeyEnum;
import com.murilovc.workly.util.AuthorityUtils;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class AppUserDetailsImpl implements AppUserDetails {

    private final Long id;

    private final String username;

    private final String password;

    private final RoleKeyEnum role;

    private final Collection<? extends GrantedAuthority> authorities;

    public AppUserDetailsImpl(final User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.authorities = AuthorityUtils.createAuthorities(user.getRole());
    }

    public AppUserDetailsImpl(final String username) {
        this.id = null;
        this.username = username;
        this.password = null;
        this.role = null;
        this.authorities = null;
    }
}
