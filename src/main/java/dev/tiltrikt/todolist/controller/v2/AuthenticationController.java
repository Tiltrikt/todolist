package dev.tiltrikt.todolist.controller.v2;

import dev.tiltrikt.todolist.dto.user.UserAuthenticationRequest;
import dev.tiltrikt.todolist.response.AuthenticationResponse;
import dev.tiltrikt.todolist.response.TodolistResponse;
import dev.tiltrikt.todolist.service.authentication.AuthenticationService;
import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TodolistResponse<AuthenticationResponse> register(
            @Valid @RequestBody UserRegistrationRequest request) {

        return TodolistResponse.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public TodolistResponse<AuthenticationResponse> authenticate(
            @Valid @RequestBody UserAuthenticationRequest request) {

        return TodolistResponse.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public TodolistResponse<AuthenticationResponse> refreshToken(HttpServletRequest request){

        return TodolistResponse.ok(service.refreshToken(request));
    }

}
