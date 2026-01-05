package kr.it.rudy.server.base64.application.dto;

public record Base64Response(
        String result,
        boolean success,
        String errorMessage
) {
}
