package kr.it.rudy.server.hash.application.dto;

public record HashResponse(
        String algorithm,
        String hash
) {
}
