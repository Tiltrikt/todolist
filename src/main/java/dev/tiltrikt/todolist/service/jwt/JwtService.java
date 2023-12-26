package dev.tiltrikt.todolist.service.jwt;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    @NotNull
    String extractUsername(@NotNull String token);

    @NotNull
    String generateAccessToken(@NotNull UserDetails userDetails);

    @NotNull
    String generateRefreshToken(@NotNull UserDetails userDetails);

    boolean isTokenValid(@NotNull String token, @NotNull String type);
}
