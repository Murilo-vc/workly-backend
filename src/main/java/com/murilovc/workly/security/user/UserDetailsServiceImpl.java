package com.murilovc.workly.security.user;

import com.murilovc.workly.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUserDetails loadUserByUsername(final String username) {
        Assert.notNull(username, "The given username must not be null");
        return this.userRepository.findOneByUsernameIgnoreCase(username)
            .map(user -> (AppUserDetails) new AppUserDetailsImpl(user))
            .orElseThrow(() -> {
                log.debug("{} not found", username);
                return new UsernameNotFoundException(username + " not found");
            });
    }
}
