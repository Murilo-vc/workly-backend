package com.murilovc.workly.config.security;

import com.auth0.jwt.interfaces.Claim;
import com.murilovc.workly.security.user.AppUserDetails;
import com.murilovc.workly.security.user.UserDetailsServiceImpl;
import com.murilovc.workly.service.impl.AuthServiceImpl;
import com.murilovc.workly.util.PathUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsServiceImpl userDetailsService;

    public TokenAuthenticationFilter(@NotNull final JwtService jwtService,
                                     @NotNull final UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if ((path.startsWith("/users") && method.equals("POST")) ||
            (path.startsWith("/companies") && method.equals("POST")) ||
            PathUtils.isPublic(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var token = recoverToken(request);

            if (token != null) {
                final Map<String, Claim> tokenClaims = this.jwtService.validateToken(token);
                final String username = this.jwtService.getUsernameFromClaims(tokenClaims);
                AppUserDetails user = this.userDetailsService.loadUserByUsername(username);

                if (user != null && AuthServiceImpl.isUserIdLoggedIn(user.getId())) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            log.debug("Cannot set user authentication - {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
