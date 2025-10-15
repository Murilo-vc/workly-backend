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

    public static final String[] WHITELIST_PATTERNS = {
            "/login",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(configure -> configure.configurationSource(this.corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST_PATTERNS).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // allow all origins (not ideal, but will have to do)
        configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL)); // allow all methods
        configuration.setAllowedOrigins(null); // remove all origins allowed entry

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
