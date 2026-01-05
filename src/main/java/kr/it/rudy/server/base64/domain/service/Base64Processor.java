package kr.it.rudy.server.base64.domain.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;

@Component
public class Base64Processor {
    public String encodeBase64(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("encode text must not be empty or null");
        }

        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public String decodeBase64(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("decode text must not be empty or null");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(text);
        return new String(decodedBytes);
    }
}
