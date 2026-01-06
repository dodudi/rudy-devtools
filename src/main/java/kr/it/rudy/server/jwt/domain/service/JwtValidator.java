package kr.it.rudy.server.jwt.domain.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.Jwts;
import kr.it.rudy.server.jwt.domain.model.Jwt;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtValidator {

    public boolean validate(Jwt jwt, String secret) {
        if (jwt == null || secret == null || secret.isBlank()) {
            return false;
        }

        try {
            SecretKey key = createSecretKey(secret);
            parseAndVerify(jwt.getToken(), key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey createSecretKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Claims parseAndVerify(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
