package kr.it.rudy.server.id.application.service;

import kr.it.rudy.server.id.application.dto.UuidResponse;
import kr.it.rudy.server.id.domain.service.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UuidService {

    private final UuidGenerator uuidGenerator;

    public UuidResponse generateUuid() {
        return new UuidResponse(uuidGenerator.generateUuid());
    }
}
