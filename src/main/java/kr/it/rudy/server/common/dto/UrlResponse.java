package kr.it.rudy.server.common.dto;

public record UrlResponse(
        String result,
        boolean success,
        String errorMessage
) {
}
