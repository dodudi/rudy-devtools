package kr.it.rudy.server.timestamp.application.dto;

/**
 * @param input          타임스탬프 혹은 날짜 문자열 데이터
 * @param conversionType 현재시간, 타임스탬프, 날짜 타입
 * @param timezone       타임존
 *
 */
public record TimestampRequest(
        String input,
        String conversionType,
        String timezone
) {
}
