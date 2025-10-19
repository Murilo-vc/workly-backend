package com.murilovc.workly.controller;

import com.murilovc.workly.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("logoutController")
@RequestMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogoutController extends BaseController {

    private final AuthService authService;

    public LogoutController(final AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Logout")
    @ApiResponses({
        @ApiResponse(responseCode = "200")
    })
    @PostMapping
    public ResponseEntity<?> logout() {
        this.authService.logout();
        return ResponseEntity.ok(Map.of("message", "OK"));
    }
}
