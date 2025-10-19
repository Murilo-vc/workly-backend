package com.murilovc.workly.controller;

import com.murilovc.workly.domain.dto.UserDto;
import com.murilovc.workly.domain.payload.UserCreatePayload;
import com.murilovc.workly.domain.payload.UserUpdatePayload;
import com.murilovc.workly.handler.exception.ForbiddenException;
import com.murilovc.workly.handler.exception.UserNotFoundException;
import com.murilovc.workly.handler.exception.UsernameAlreadyExistsException;
import com.murilovc.workly.handler.exception.ValidationErrorException;
import com.murilovc.workly.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController("userController")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401"),
        @ApiResponse(responseCode = "403"),
        @ApiResponse(responseCode = "404")
    })
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable final Long id) {
        try {
            final UserDto user = this.userService.getUserById(id);

            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | ForbiddenException e) {
            return ResponseEntity
                .status(e.getStatus())
                .body(Map.of("message", e.getMessage()));
        }
    }

    @Operation(summary = "Create new user")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "409"),
        @ApiResponse(responseCode = "422")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserCreatePayload payload) {
        try {
            this.userService.create(payload);
            return ResponseEntity.ok(Map.of("message", "Created"));
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(409)
                .body(Map.of("message", e.getMessage()));
        } catch (ValidationErrorException e) {
            return ResponseEntity.status(422)
                .body(
                    Map.of(
                        "message", e.getMessage(),
                        "code", "UNPROCESSABLE",
                        "details", e.getDetails()
                    ));
        }
    }

    @Operation(summary = "Delete user account")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401"),
        @ApiResponse(responseCode = "403"),
        @ApiResponse(responseCode = "404"),
        @ApiResponse(responseCode = "422")
    })
    @PatchMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable final Long id,
                                        @Valid @RequestBody final UserUpdatePayload payload) {
        try {
            this.userService.update(id, payload);

            return ResponseEntity.ok(null);
        } catch (UserNotFoundException | ForbiddenException e) {
            return ResponseEntity
                .status(e.getStatus())
                .body(
                    Map.of("message", e.getMessage())
                );
        } catch (ValidationErrorException e) {
            return ResponseEntity.status(422)
                .body(
                    Map.of(
                        "message", e.getMessage(),
                        "code", "UNPROCESSABLE",
                        "details", e.getDetails()
                    ));
        }
    }

    @Operation(summary = "Delete user account")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401"),
        @ApiResponse(responseCode = "403"),
        @ApiResponse(responseCode = "404")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.userService.delete(id);

            return ResponseEntity.ok(
                Map.of("message", "User deleted successfully")
            );
        } catch (UserNotFoundException | ForbiddenException e) {
            return ResponseEntity
                .status(e.getStatus())
                .body(Map.of("message", e.getMessage()));
        }
    }
}
