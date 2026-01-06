package kr.it.rudy.server.json.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.json.application.dto.JsonParserRequest;
import kr.it.rudy.server.json.application.dto.JsonParserResponse;
import kr.it.rudy.server.json.domain.service.JsonParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json")
public class JsonParserController {
    private final JsonParserService jsonParserService;

    @PostMapping("/parse")
    public ResponseEntity<ApiResponse<JsonParserResponse>> jsonParser(@Valid @RequestBody JsonParserRequest request) {
        JsonParserResponse jsonParserResponse = jsonParserService.jsonParser(request.json(), request.indent());
        return ResponseEntity.ok(ApiResponse.ok(jsonParserResponse));
    }
}
