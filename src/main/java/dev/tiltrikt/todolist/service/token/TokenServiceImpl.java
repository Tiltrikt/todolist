package dev.tiltrikt.todolist.service.token;

import dev.tiltrikt.todolist.model.Token;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.TokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenServiceImpl implements TokenService {

    TokenRepository tokenRepository;

    public boolean isTokenRevoked(@NotNull String token) {

        return tokenRepository.findByToken(token)
                .map(Token::isRevoked)
                .orElse(true);
    }

    public void revokeAllUserTokens(@NotNull User user) {

        List<Token> validUserTokens = tokenRepository.findAllByRevokedFalseAndUserId(user.getId());

        for (Token token : validUserTokens) {
            token.setRevoked(true);
        }
        tokenRepository.saveAll(validUserTokens);
    }

    public void saveUserToken(@NotNull User user, @NotNull String jwtToken) {

        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .build();

        tokenRepository.save(token);
    }
}
