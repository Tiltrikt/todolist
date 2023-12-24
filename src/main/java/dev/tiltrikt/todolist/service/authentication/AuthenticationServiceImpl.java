package dev.tiltrikt.todolist.service.authentication;

import dev.tiltrikt.todolist.exception.TokenException;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.service.jwt.JwtService;
import dev.tiltrikt.todolist.dto.user.UserAuthenticationRequest;
import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.model.Token;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.TokenRepository;
import dev.tiltrikt.todolist.repository.UserRepository;
import dev.tiltrikt.todolist.response.AuthenticationResponse;
import dev.tiltrikt.todolist.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService{

    UserRepository repository;
    TokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    public AuthenticationResponse register(UserRegistrationRequest request) throws UserException {

        Optional<User> optionalUser = repository.findUserByUsername(request.getUsername());
        if (optionalUser.isPresent())
            throw new UserException("user with username: " + request.getUsername() + " already exists");

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserDetails userDetails = new UserDetailsImpl(user);
        User savedUser = repository.save(user);
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(UserAuthenticationRequest request) throws UserException {

        User user = repository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new UserException("no user with username: " + request.getUsername() + " found"));

        UserDetails userDetails = new UserDetailsImpl(user);
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {

        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {

        List<Token> validUserTokens = tokenRepository.findAllByRevokedFalseAndUserId(user.getId());

        for (Token token : validUserTokens) {
            token.setRevoked(true);
        }
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException, TokenException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new AuthenticationCredentialsNotFoundException("no token present");

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username == null) throw new TokenException("token not found");

        //noinspection OptionalGetWithoutIsPresent
        User user = this.repository.findUserByUsername(username).get();
        UserDetails userDetails = new UserDetailsImpl(user);

        if (!jwtService.isTokenValid(refreshToken, userDetails)) throw new TokenException("token is expired or revoked");

        String accessToken = jwtService.generateToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}