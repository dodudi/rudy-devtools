package kr.it.rudy.server.regex.presentation.controller;

import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.regex.application.dto.RegexRequest;
import kr.it.rudy.server.regex.application.dto.RegexResponse;
import kr.it.rudy.server.regex.domain.service.RegexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regex")
public class RegexController {
    private final RegexService regexService;

    @PostMapping
    public ResponseEntity<ApiResponse<RegexResponse>> regexTest(@RequestBody RegexRequest request) {
        RegexResponse regexResponse = regexService.testRegex(request.pattern(), request.text(), request.caseInsensitive(), request.multiline(), request.dotAll());
        return ResponseEntity.ok(ApiResponse.ok(regexResponse));
    }
}
