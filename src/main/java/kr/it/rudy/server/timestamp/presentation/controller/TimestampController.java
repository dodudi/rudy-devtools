package kr.it.rudy.server.timestamp.presentation.controller;

import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.timestamp.application.dto.TimestampRequest;
import kr.it.rudy.server.timestamp.application.dto.TimestampResponse;
import kr.it.rudy.server.timestamp.application.service.TimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timestamp")
@RequiredArgsConstructor
public class TimestampController {
    private final TimestampService timestampService;

    @PostMapping
    public ResponseEntity<ApiResponse<TimestampResponse>> convertTimestamp(@RequestBody TimestampRequest request) {
        TimestampResponse timestampResponse = timestampService.convertTimestamp(request.input(), request.conversionType(), request.timezone());
        return ResponseEntity.ok(ApiResponse.ok(timestampResponse));
    }
}
