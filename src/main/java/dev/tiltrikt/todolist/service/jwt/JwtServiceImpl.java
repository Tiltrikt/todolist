package dev.tiltrikt.todolist.service.jwt;

import dev.tiltrikt.todolist.service.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.key}")
    String secretKey;
    @Value("${jwt.lifetime.access-token}")
    int jwtExpiration;
    @Value("${jwt.lifetime.refresh-token}")
    int refreshExpiration;

    TokenService tokenService;

    @NotNull
    public String extractUsername(@NotNull String token) {
        return extractAllClaims(token).getSubject();
    }

    @NotNull
    public String generateAccessToken(@NotNull UserDetails userDetails) {
        return buildToken(Map.of("type", "access"), userDetails, jwtExpiration);
    }

    @NotNull
    public String generateRefreshToken(@NotNull UserDetails userDetails) {
        return buildToken(Map.of("type", "access"), userDetails, refreshExpiration);
    }

    public boolean isTokenValid(@NotNull String token, @NotNull String type) {

        boolean appropriateType = type
                .equals(extractAllClaims(token).get("type", String.class));

        boolean revoked = tokenService.isTokenRevoked(token);

        boolean expired = extractAllClaims(token).getExpiration()
                .before(new Date(System.currentTimeMillis()));

        return (!revoked && !expired && appropriateType);
    }

    @NotNull
    private String buildToken(@NotNull Map<String, String> extraClaims,
                              @NotNull UserDetails userDetails, int expiration) {

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    @NotNull
    private Claims extractAllClaims(@NotNull String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @NotNull
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
