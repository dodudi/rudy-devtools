package kr.it.rudy.server.timestamp.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class TimestampProcessor {

    private static final DateTimeFormatter[] formatters = {
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    public ZonedDateTime parseDateTime(String input, ZoneId zoneId) {
        for (DateTimeFormatter formatter : formatters) {
            if (input.contains("T") || input.contains("Z") || input.contains("+")) {
                return ZonedDateTime.parse(input, formatter);
            } else {
                LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
                return localDateTime.atZone(zoneId);
            }
        }

        throw new DateTimeParseException("Unable to parse date", input, 0);
    }

    public ZonedDateTime convertEpochTime(String input, ZoneId zoneId) {
        long timestamp = Long.parseLong(input.trim());
        if (timestamp < 10000000000L) {
            return Instant.ofEpochSecond(timestamp).atZone(zoneId);
        } else {
            return Instant.ofEpochMilli(timestamp).atZone(zoneId);
        }
    }

    public String formatKorean(ZonedDateTime dateTime) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        String amPm = hour < 12 ? "오전" : "오후";
        int hour12 = hour % 12;
        if (hour12 == 0) hour12 = 12;

        return String.format("%d년 %d월 %d일 %s %d시 %02d분 %02d초", year, month, day, amPm, hour12, minute, second);
    }
}
