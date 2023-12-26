package dev.tiltrikt.todolist.service.token;

import dev.tiltrikt.todolist.model.User;
import org.jetbrains.annotations.NotNull;

public interface TokenService {

    boolean isTokenRevoked(@NotNull String token);

    void revokeAllUserTokens(@NotNull User user);

    void saveUserToken(@NotNull User user, @NotNull String jwtToken);

}
