package kr.it.rudy.server.devtools.presentation.controller;

import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.common.dto.JsonParserRequest;
import kr.it.rudy.server.common.dto.JsonParserResponse;
import kr.it.rudy.server.devtools.application.service.DevToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devtools")
public class DevToolController {
    private final DevToolService devToolService;

    @PostMapping("/json-parser")
    public ResponseEntity<ApiResponse<JsonParserResponse>> jsonParser(@RequestBody JsonParserRequest request) {
        JsonParserResponse response = devToolService.jsonParser(request.json(), request.indent());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
