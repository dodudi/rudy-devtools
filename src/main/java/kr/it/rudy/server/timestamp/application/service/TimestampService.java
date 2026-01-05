package kr.it.rudy.server.timestamp.application.service;

import kr.it.rudy.server.timestamp.application.dto.TimestampResponse;
import kr.it.rudy.server.timestamp.domain.service.TimestampProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class TimestampService {

    private final TimestampProcessor timestampProcessor;

    public TimestampResponse convertTimestamp(String input, String conversionType, String timezone) {
        try {
            ZoneId zoneId = (timezone == null || timezone.isEmpty()) ? ZoneId.systemDefault() : ZoneId.of(timezone);
            ZonedDateTime dateTime;

            if ("CURRENT_TIME".equals(conversionType)) {
                dateTime = ZonedDateTime.now(zoneId);
            } else if ("TO_DATE".equals(conversionType)) {
                if (!StringUtils.hasText(input)) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "입력값이 비어있습니다.");
                }
                try {
                    long timestamp = Long.parseLong(input.trim());
                    if (timestamp < 10000000000L) {
                        dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId);
                    } else {
                        dateTime = Instant.ofEpochMilli(timestamp).atZone(zoneId);
                    }
                } catch (NumberFormatException e) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "유효하지 않은 타임스탬프 숫자입니다.");
                } catch (Exception e) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "타임스탬프 변환 실패: " + e.getMessage());
                }
            } else if ("TO_TIMESTAMP".equals(conversionType)) {
                if (input == null || input.isEmpty()) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "입력값이 비어있습니다.");
                }
                try {
                    dateTime = parseDateTime(input.trim(), zoneId);
                } catch (DateTimeParseException e) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "날짜 형식이 잘못되었습니다. ISO 8601 형식(예: 2026-01-03T15:30:00)을 사용하세요.");
                } catch (Exception e) {
                    return new TimestampResponse(null, null, null, null, null, null, false, "날짜 파싱 실패: " + e.getMessage());
                }
            } else {
                return new TimestampResponse(null, null, null, null, null, null, false, "유효하지 않은 변환 타입입니다.");
            }

            long millis = dateTime.toInstant().toEpochMilli();
            long seconds = dateTime.toInstant().getEpochSecond();
            String iso8601 = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String formatted = formatKorean(dateTime);
            String rfc2822 = dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
            String timezoneStr = dateTime.getZone().getId();

            return new TimestampResponse(millis, seconds, iso8601, formatted, rfc2822, timezoneStr, true, null);

        } catch (Exception e) {
            return new TimestampResponse(null, null, null, null, null, null, false, "변환 중 오류 발생: " + e.getMessage());
        }
    }

    private ZonedDateTime parseDateTime(String input, ZoneId zoneId) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME,
                DateTimeFormatter.ISO_ZONED_DATE_TIME,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (input.contains("T") || input.contains("Z") || input.contains("+")) {
                    return ZonedDateTime.parse(input, formatter);
                } else {
                    LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
                    return localDateTime.atZone(zoneId);
                }
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new DateTimeParseException("Unable to parse date", input, 0);
    }

    private String formatKorean(ZonedDateTime dateTime) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        String amPm = hour < 12 ? "오전" : "오후";
        int hour12 = hour % 12;
        if (hour12 == 0) hour12 = 12;

        return String.format("%d년 %d월 %d일 %s %d시 %02d분 %02d초",
                year, month, day, amPm, hour12, minute, second);
    }
}
