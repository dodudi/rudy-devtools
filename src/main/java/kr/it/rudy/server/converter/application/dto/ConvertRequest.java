package kr.it.rudy.server.converter.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record ConvertRequest(
        @NotEmpty(message = "content must not be empty or null") String content,
        Integer indent
) {
    public ConvertRequest {
        if (indent == null) {
            indent = 2;
        }
    }
}
