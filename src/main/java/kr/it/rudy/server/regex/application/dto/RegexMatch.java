package kr.it.rudy.server.regex.application.dto;

import java.util.List;

public record RegexMatch(
        String match,
        int start,
        int end,
        List<String> groups
) {
}
