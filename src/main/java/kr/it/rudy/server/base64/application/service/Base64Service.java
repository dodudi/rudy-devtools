package kr.it.rudy.server.base64.application.service;

import kr.it.rudy.server.base64.application.dto.Base64Response;
import kr.it.rudy.server.base64.domain.service.Base64Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Base64Service {

    private final Base64Processor base64Processor;

    public Base64Response encodeBase64(String text) {
        return new Base64Response(base64Processor.encodeBase64(text));
    }

    public Base64Response decodeBase64(String text) {
        return new Base64Response(base64Processor.decodeBase64(text));
    }
}
