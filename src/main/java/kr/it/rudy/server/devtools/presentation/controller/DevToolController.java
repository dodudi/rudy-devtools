package kr.it.rudy.server.devtools.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.*;
import kr.it.rudy.server.devtools.application.service.DevToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/base64-encode")
    public ResponseEntity<ApiResponse<Base64Response>> base64Encode(@Valid @RequestBody Base64Request request) {
        Base64Response response = devToolService.encodeBase64(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/base64-decode")
    public ResponseEntity<ApiResponse<Base64Response>> base64Decode(@Valid @RequestBody Base64Request request) {
        Base64Response response = devToolService.decodeBase64(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/url-encode")
    public ResponseEntity<ApiResponse<UrlResponse>> urlEncode(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = devToolService.encodeUrl(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/url-decode")
    public ResponseEntity<ApiResponse<UrlResponse>> urlDecode(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = devToolService.decodeUrl(request.text());
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/regex-test")
    public ResponseEntity<ApiResponse<RegexResponse>> regexTest(@Valid @RequestBody RegexRequest request) {
        RegexResponse response = devToolService.testRegex(
                request.pattern(),
                request.text(),
                request.caseInsensitive(),
                request.multiline(),
                request.dotAll()
        );
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
