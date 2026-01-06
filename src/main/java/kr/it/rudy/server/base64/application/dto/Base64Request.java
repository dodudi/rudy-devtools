package kr.it.rudy.server.base64.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record Base64Request(
        @NotEmpty(message = "text must not be empty or null") String text
) {
}
