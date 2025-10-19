package com.murilovc.workly.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.murilovc.workly.domain.entity.User;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class JwtService {
    @Value("${api.jwt.token.secret}")
    private String secret;

    @Getter
    @Value("${api.jwt.token.expiration}")
    private int tokenExpiration;

    public String generateToken(final User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            final Instant tokenExpiration = this.getTokenExpirationDate();

            return JWT.create()
                .withIssuer("auth0")
                .withClaim("sub", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole().getId())
                .withClaim("exp", tokenExpiration)
                .withExpiresAt(tokenExpiration)
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public Map<String, Claim> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("auth0")
                .build()
                .verify(token)
                .getClaims();
        } catch (JWTCreationException | JWTDecodeException e) {
            return Map.of();
        }
    }

    public String getUsernameFromClaims(@NonNull final Map<String, Claim> claims) {
        final Claim claim = claims.getOrDefault("username", null);

        if (claim == null) {
            return "";
        }

        return claim.asString();
    }

    public Instant getTokenExpirationDate() {
        return LocalDateTime.now()
            .plus(this.tokenExpiration, ChronoUnit.MILLIS)
            .toInstant(ZoneOffset.of("-03:00"));
    }
}
