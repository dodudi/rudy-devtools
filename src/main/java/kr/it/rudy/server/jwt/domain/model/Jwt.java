package kr.it.rudy.server.jwt.domain.model;

import lombok.Getter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@Getter
public class Jwt {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final int EXPECTED_PARTS = 3;

    private final String token;
    private final Map<String, Object> header;
    private final Map<String, Object> payload;
    private final String signature;

    private Jwt(String token, Map<String, Object> header, Map<String, Object> payload, String signature) {
        this.token = token;
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public static Jwt from(String token) {
        validateTokenFormat(token);

        String[] parts = token.split("\\.");
        if (parts.length != EXPECTED_PARTS) {
            throw new IllegalArgumentException("Invalid JWT token format: expected 3 parts but got " + parts.length);
        }

        Map<String, Object> header = decodeBase64ToMap(parts[0]);
        Map<String, Object> payload = decodeBase64ToMap(parts[1]);
        String signature = parts[2];

        return new Jwt(token, header, payload, signature);
    }

    private static void validateTokenFormat(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("JWT token must not be null or blank");
        }
    }

    private static Map<String, Object> decodeBase64ToMap(String base64) {
        try {
            String decoded = new String(Base64.getUrlDecoder().decode(base64));
            return OBJECT_MAPPER.readValue(decoded, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decode JWT part: " + e.getMessage(), e);
        }
    }

    public String getAlgorithm() {
        return (String) header.getOrDefault("alg", "Unknown");
    }

    public String getType() {
        return (String) header.getOrDefault("typ", "JWT");
    }

    public Long getIssuedAt() {
        return extractTimestamp("iat");
    }

    public Long getExpiresAt() {
        return extractTimestamp("exp");
    }

    public boolean isExpired() {
        Long expiresAt = getExpiresAt();
        if (expiresAt == null) {
            return false;
        }
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        return currentTimeInSeconds > expiresAt;
    }

    private Long extractTimestamp(String key) {
        Object value = payload.get(key);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return null;
    }

    public Map<String, Object> getHeader() {
        return Map.copyOf(header);
    }

    public Map<String, Object> getPayload() {
        return Map.copyOf(payload);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jwt jwt = (Jwt) o;
        return Objects.equals(token, jwt.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "Jwt{algorithm='" + getAlgorithm() + "', type='" + getType() + "', isExpired=" + isExpired() + "}";
    }
}
