package kr.it.rudy.server.hash.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.hash.application.dto.HashRequest;
import kr.it.rudy.server.hash.application.dto.HashResponse;
import kr.it.rudy.server.hash.application.dto.HashVerifyRequest;
import kr.it.rudy.server.hash.application.dto.HashVerifyResponse;
import kr.it.rudy.server.hash.application.service.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hash")
public class HashController {

    private final HashService hashService;

    @PostMapping
    public ResponseEntity<ApiResponse<HashResponse>> hash(@Valid @RequestBody HashRequest request) {
        HashResponse response = hashService.hash(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<HashVerifyResponse>> verify(@Valid @RequestBody HashVerifyRequest request) {
        HashVerifyResponse response = hashService.verify(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
