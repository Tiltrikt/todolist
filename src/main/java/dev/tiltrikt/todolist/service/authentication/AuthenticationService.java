package dev.tiltrikt.todolist.service.authentication;

import dev.tiltrikt.todolist.exception.TokenException;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.dto.user.UserAuthenticationRequest;
import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public interface AuthenticationService {

    AuthenticationResponse register(UserRegistrationRequest request) throws UserException;

    AuthenticationResponse authenticate(UserAuthenticationRequest request) throws UserException;

    AuthenticationResponse refreshToken(HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException, TokenException;
}
