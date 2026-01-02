package kr.it.rudy.server.common.dto;

import java.util.List;

public record RegexResponse(
        boolean isValid,
        List<RegexMatch> matches,
        int matchCount,
        boolean success,
        String errorMessage
) {
}
