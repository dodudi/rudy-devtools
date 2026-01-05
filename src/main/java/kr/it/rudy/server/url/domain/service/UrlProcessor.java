package kr.it.rudy.server.url.domain.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class UrlProcessor {
    public String encodeUrl(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("text must not be empty or null");
        }

        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    public String decodeUrl(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("text must not be empty or null");
        }

        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}
