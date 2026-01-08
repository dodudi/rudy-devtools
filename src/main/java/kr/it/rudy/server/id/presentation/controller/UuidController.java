package kr.it.rudy.server.id.presentation.controller;

import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.id.application.dto.UuidResponse;
import kr.it.rudy.server.id.application.service.UuidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uuid")
public class UuidController {

    private final UuidService uuidService;

    @GetMapping("/generate")
    public ResponseEntity<ApiResponse<UuidResponse>> generateUuid() {
        UuidResponse response = uuidService.generateUuid();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
