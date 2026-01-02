package kr.it.rudy.server.common.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * @param pattern         regex pattern
 * @param text            text to match
 * @param caseInsensitive 대소문자 구분 안함
 * @param multiline       멀티라인 모드 (^와 $가 각 줄의 시작/끝)
 * @param dotAll          패턴이 줄바꿈 포함 모든 문자 매칭
 *
 */
public record RegexRequest(
        @NotEmpty(message = "pattern must not be empty or null") String pattern,
        @NotEmpty(message = "text must not be empty or null") String text,
        Boolean caseInsensitive,
        Boolean multiline,
        Boolean dotAll
) {
}
