package kr.it.rudy.server.base64.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.base64.application.service.Base64Service;
import kr.it.rudy.server.base64.domain.service.Base64Processor;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.base64.application.dto.Base64Request;
import kr.it.rudy.server.base64.application.dto.Base64Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/base64")
public class Base64Controller {

    private final Base64Service base64Service;

    @PostMapping("/encode")
    public ResponseEntity<ApiResponse<Base64Response>> base64Encode(@Valid @RequestBody Base64Request request) {
        Base64Response response = base64Service.encodeBase64(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/decode")
    public ResponseEntity<ApiResponse<Base64Response>> base64Decode(@Valid @RequestBody Base64Request request) {
        Base64Response response = base64Service.decodeBase64(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
