package kr.it.rudy.server.url.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.url.application.dto.UrlRequest;
import kr.it.rudy.server.url.application.dto.UrlResponse;
import kr.it.rudy.server.url.application.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/url")
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/encode")
    public ResponseEntity<ApiResponse<UrlResponse>> urlEncode(@Valid @RequestBody UrlRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(urlService.encodeUrl(request.text())));
    }

    @PostMapping("/decode")
    public ResponseEntity<ApiResponse<UrlResponse>> urlDecode(@Valid @RequestBody UrlRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(urlService.decodeUrl(request.text())));
    }
}
