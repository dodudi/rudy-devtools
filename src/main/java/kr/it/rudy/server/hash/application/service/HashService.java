package kr.it.rudy.server.hash.application.service;

import kr.it.rudy.server.hash.application.dto.HashRequest;
import kr.it.rudy.server.hash.application.dto.HashResponse;
import kr.it.rudy.server.hash.application.dto.HashVerifyRequest;
import kr.it.rudy.server.hash.application.dto.HashVerifyResponse;
import kr.it.rudy.server.hash.domain.service.HashProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashService {

    private final HashProcessor hashProcessor;

    public HashResponse hash(HashRequest request) {
        String hash = hashProcessor.hash(request.text(), request.algorithm());
        return new HashResponse(request.algorithm().name(), hash);
    }

    public HashVerifyResponse verify(HashVerifyRequest request) {
        String actualHash = hashProcessor.hash(request.text(), request.algorithm());
        boolean valid = hashProcessor.verify(request.text(), request.algorithm(), request.expectedHash());
        return new HashVerifyResponse(request.algorithm().name(), valid, actualHash);
    }
}
