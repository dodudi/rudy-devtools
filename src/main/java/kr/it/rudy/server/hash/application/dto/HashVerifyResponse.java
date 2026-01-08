package kr.it.rudy.server.hash.application.dto;

public record HashVerifyResponse(
        String algorithm,
        boolean valid,
        String actualHash
) {
}
