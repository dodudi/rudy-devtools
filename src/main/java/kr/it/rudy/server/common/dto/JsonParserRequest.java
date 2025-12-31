package kr.it.rudy.server.common.dto;

public record JsonParserRequest(
        String json,
        Integer indent
) {
}
