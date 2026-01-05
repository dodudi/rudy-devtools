package kr.it.rudy.server.json.application.dto;

public record JsonParserRequest(
        String json,
        Integer indent
) {
}
