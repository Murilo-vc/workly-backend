package com.murilovc.workly.security.user;

import com.murilovc.workly.domain.enumeration.RoleKeyEnum;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserDetails extends UserDetails {

    Long getId();

    RoleKeyEnum getRole();
}
