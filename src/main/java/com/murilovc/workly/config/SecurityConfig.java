package com.murilovc.workly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    static final String PRIVATE_API_PATTERN = "/api/private/**";

    static final String PUBLIC_API_PATTERN = "/api/public/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(PRIVATE_API_PATTERN, createCorsConfig(allowedOriginsPrivate));
        source.registerCorsConfiguration(PUBLIC_API_PATTERN, createCorsConfig(allowedOriginsPublic));

        return source;
    }

    private CorsConfiguration createCorsConfig(String allowedOrigins) {
        PatternedOriginCorsConfig configuration = new PatternedOriginCorsConfig();
        configuration.applyPermitDefaultValues(); // applies basic CORS config
        configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL)); // allow all methods
        configuration.setAllowedOrigins(null); // remove all origins allowed entry
        List<String> originsConfig = List.of(allowedOrigins.split(","));
        for (String originAllowed : originsConfig) {
            configuration.addAllowedOrigin(originAllowed.trim());
        }
        return configuration;
    }
}
