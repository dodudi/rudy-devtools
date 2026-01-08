package kr.it.rudy.server.id.domain.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator {
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
