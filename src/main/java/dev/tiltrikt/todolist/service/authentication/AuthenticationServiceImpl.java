package dev.tiltrikt.todolist.service.authentication;

import dev.tiltrikt.todolist.exception.TokenException;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.service.jwt.JwtService;
import dev.tiltrikt.todolist.dto.user.UserAuthenticationRequest;
import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.response.AuthenticationResponse;
import dev.tiltrikt.todolist.security.UserDetailsImpl;
import dev.tiltrikt.todolist.service.token.TokenService;
import dev.tiltrikt.todolist.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService{

    UserService userService;
    TokenService tokenService;
    JwtService jwtService;

    public AuthenticationResponse register(UserRegistrationRequest request) throws UserException {

        User user = userService.addUser(request);
        UserDetails userDetails = new UserDetailsImpl(user);

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        tokenService.saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse authenticate(UserAuthenticationRequest request) throws UserException {

        User user = userService.getUser(request.getUsername());
        UserDetails userDetails = new UserDetailsImpl(user);

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException, TokenException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new AuthenticationCredentialsNotFoundException("no token present");

        String refreshToken = authHeader.substring(7);
        if (jwtService.isTokenValid(refreshToken, "refresh"))
            throw new TokenException("token is not valid");

        String username = jwtService.extractUsername(refreshToken);
        User user = userService.getUser(username);
        UserDetails userDetails = new UserDetailsImpl(user);

        String accessToken = jwtService.generateAccessToken(userDetails);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }
}