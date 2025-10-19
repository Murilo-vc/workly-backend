package com.murilovc.workly.service.impl;

import com.murilovc.workly.config.security.JwtService;
import com.murilovc.workly.domain.dto.LoginDto;
import com.murilovc.workly.domain.entity.User;
import com.murilovc.workly.domain.payload.LoginPayload;
import com.murilovc.workly.handler.exception.InvalidCredentialsException;
import com.murilovc.workly.repository.UserRepository;
import com.murilovc.workly.security.user.AppUserDetails;
import com.murilovc.workly.service.AuthService;
import com.murilovc.workly.util.SecurityUtils;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service("authService")
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final static Set<Long> loggedUserIds = new HashSet<>();

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public AuthServiceImpl(final JwtService jwtService,
                           final UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public static boolean isUserIdLoggedIn(final Long userId) {
        return loggedUserIds.contains(userId);
    }

    @Override
    public LoginDto login(@NonNull @NotNull final LoginPayload payload) throws InvalidCredentialsException {
        final String username = payload.getUsername();
        final String password = payload.getPassword();

        final Optional<User> possibleUser = this.userRepository.findOneByUsernameIgnoreCase(username);

        if (possibleUser.isPresent()) {
            final User user = possibleUser.get();

            if (!user.getPassword().equals(password)) {
                throw new InvalidCredentialsException();
            }

            final String token = this.jwtService.generateToken(user);
            final int expiresIn = this.jwtService.getTokenExpiration();

            loggedUserIds.add(user.getId());

            return LoginDto.toDto(token, expiresIn);
        }

        throw new InvalidCredentialsException();
    }

    @Override
    public void logout() {
        final AppUserDetails currentUser = SecurityUtils.getCurrentUser();
        final Long userId = currentUser.getId();
        loggedUserIds.remove(userId);
    }
}
