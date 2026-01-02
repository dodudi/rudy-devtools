package kr.it.rudy.server.common.dto;

import jakarta.validation.constraints.NotEmpty;

public record UrlRequest(
        @NotEmpty(message = "text must not be empty or null") String text
) {
}
