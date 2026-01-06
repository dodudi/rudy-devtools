package kr.it.rudy.server.regex.application.dto;

import java.util.List;

public record RegexResponse(
        boolean isValid,
        List<RegexMatch> matches,
        int matchCount
) {
}
