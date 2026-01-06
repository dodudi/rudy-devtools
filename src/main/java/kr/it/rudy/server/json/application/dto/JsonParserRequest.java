package kr.it.rudy.server.json.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record JsonParserRequest(
        @NotEmpty(message = "json must not be empty or null") String json,
        Integer indent
) {
}
