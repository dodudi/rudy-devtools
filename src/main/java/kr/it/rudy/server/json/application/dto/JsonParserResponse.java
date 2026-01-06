package kr.it.rudy.server.json.application.dto;

public record JsonParserResponse(
        String formattedJson,
        String minifiedJson,
        boolean isValid
) {
}
