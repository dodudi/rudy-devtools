package kr.it.rudy.server.url.application.dto;

public record UrlResponse(
        String result,
        boolean success,
        String errorMessage
) {
}
