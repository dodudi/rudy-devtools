package kr.it.rudy.server.url.application.service;

import kr.it.rudy.server.url.application.dto.UrlResponse;
import kr.it.rudy.server.url.domain.service.UrlProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlProcessor urlProcessor;

    public UrlResponse encodeUrl(String text) {
        String encodeUrl = urlProcessor.encodeUrl(text);
        return new UrlResponse(encodeUrl);
    }

    public UrlResponse decodeUrl(String text) {
        String decodeUrl = urlProcessor.decodeUrl(text);
        return new UrlResponse(decodeUrl);
    }
}
