package com.murilovc.workly.config.security;

import com.murilovc.workly.util.PathUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String path = request.getRequestURI();
        final String method = request.getMethod();
        if ((path.startsWith("/users") && method.equals("POST")) ||
            (path.startsWith("/companies") && method.equals("POST")) ||
            PathUtils.isPublic(path)) {
            return;
        }

        log.warn("Unauthorized error - {}", authException.getMessage());
        System.out.println("DEBUG -> Entrou no AuthenticationEntryPointImpl");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{ \"message\": \"Invalid token\" }");
    }
}
