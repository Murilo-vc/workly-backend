package com.murilovc.workly.controller;

import com.murilovc.workly.domain.dto.LoginDto;
import com.murilovc.workly.domain.payload.LoginPayload;
import com.murilovc.workly.handler.exception.InvalidCredentialsException;
import com.murilovc.workly.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Validated
@RestController("loginController")
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController extends BaseController {

    private final AuthService authService;

    public LoginController(final AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Login")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401")
    })
    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginPayload payload) {
        try {
            final LoginDto response = this.authService.login(payload);
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(e.getStatus())
                .body(
                    Map.of("message", e.getMessage())
                );
        }
    }
}
