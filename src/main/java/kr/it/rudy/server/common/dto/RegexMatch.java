package kr.it.rudy.server.common.dto;

import java.util.List;

public record RegexMatch(
        String match,
        int start,
        int end,
        List<String> groups
) {
}
