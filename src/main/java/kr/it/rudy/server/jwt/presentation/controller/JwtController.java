package kr.it.rudy.server.jwt.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.jwt.application.dto.JwtDecodeRequest;
import kr.it.rudy.server.jwt.application.dto.JwtDecodeResponse;
import kr.it.rudy.server.jwt.application.dto.JwtRequest;
import kr.it.rudy.server.jwt.application.dto.JwtVerifyResponse;
import kr.it.rudy.server.jwt.application.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/decode")
    public ResponseEntity<ApiResponse<JwtDecodeResponse>> decodeJwt(@Valid @RequestBody JwtDecodeRequest request) {
        JwtDecodeResponse response = jwtService.decodeJwt(request.token());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<JwtVerifyResponse>> verifyJwt(@Valid @RequestBody JwtRequest request) {
        JwtVerifyResponse response = jwtService.verifyJwt(request.token(), request.secretKey());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
