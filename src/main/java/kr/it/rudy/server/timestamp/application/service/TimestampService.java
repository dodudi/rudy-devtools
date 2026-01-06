package kr.it.rudy.server.timestamp.application.service;

import kr.it.rudy.server.timestamp.application.dto.TimestampResponse;
import kr.it.rudy.server.timestamp.domain.service.TimestampProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TimestampService {

    private final TimestampProcessor timestampProcessor;

    public TimestampResponse convertTimestamp(String input, String conversionType, String timezone) {
        if (!StringUtils.hasText(input)) {
            throw new IllegalArgumentException("input must not be empty or null");
        }

        ZoneId zoneId = (timezone == null || timezone.isEmpty()) ? ZoneId.systemDefault() : ZoneId.of(timezone);
        ZonedDateTime dateTime;

        if ("TO_DATE".equals(conversionType)) {
            dateTime = timestampProcessor.convertEpochTime(input.trim(), zoneId);
        } else if ("TO_TIMESTAMP".equals(conversionType)) {
            dateTime = timestampProcessor.parseDateTime(input.trim(), zoneId);
        } else {
            throw new IllegalArgumentException("conversionType must be one of CURRENT_TIME, TO_DATE, TO_TIMESTAMP");
        }

        long millis = dateTime.toInstant().toEpochMilli();
        long seconds = dateTime.toInstant().getEpochSecond();
        String iso8601 = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String formatted = timestampProcessor.formatKorean(dateTime);
        String rfc2822 = dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String timezoneStr = dateTime.getZone().getId();
        return new TimestampResponse(millis, seconds, iso8601, formatted, rfc2822, timezoneStr);
    }
}
