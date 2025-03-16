package com.ats.todolist.controller;

import com.ats.todolist.dto.AuthResponseDto;
import com.ats.todolist.dto.LoginDto;
import com.ats.todolist.dto.UserRegistrationDto;
import com.ats.todolist.service.AuthService;
import com.ats.todolist.utils.ApiResponse;
import com.ats.todolist.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Authorization Controller", description = "Operations for user registration and Login ")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "User registration", description = "This endpoint is used for user registration")
    @PostMapping("/register")
    public ApiResponse<String> register(@Validated @RequestBody UserRegistrationDto userRegistrationDto){
        String response = authService.userRegistration(userRegistrationDto);
        return DefaultResponse.displayCreatedObject(response);
    }

    @Operation(summary = "User login", description = "This endpoint is used for logging-in a user")
    @PostMapping("/login")
    public ApiResponse<AuthResponseDto> login(@Validated @RequestBody LoginDto loginDto){
        AuthResponseDto response = authService.login(loginDto);
        return DefaultResponse.displayCreatedObject(response);
    }
}
