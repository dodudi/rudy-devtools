package kr.it.rudy.server.converter.presentation.controller;

import jakarta.validation.Valid;
import kr.it.rudy.server.common.dto.ApiResponse;
import kr.it.rudy.server.converter.application.dto.ConvertRequest;
import kr.it.rudy.server.converter.application.dto.ConvertResponse;
import kr.it.rudy.server.converter.application.service.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/convert")
public class ConverterController {

    private final ConverterService converterService;

    @PostMapping("/json-to-xml")
    public ResponseEntity<ApiResponse<ConvertResponse>> jsonToXml(@Valid @RequestBody ConvertRequest request) {
        ConvertResponse response = converterService.jsonToXml(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/xml-to-json")
    public ResponseEntity<ApiResponse<ConvertResponse>> xmlToJson(@Valid @RequestBody ConvertRequest request) {
        ConvertResponse response = converterService.xmlToJson(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/yaml-to-json")
    public ResponseEntity<ApiResponse<ConvertResponse>> yamlToJson(@Valid @RequestBody ConvertRequest request) {
        ConvertResponse response = converterService.yamlToJson(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/json-to-yaml")
    public ResponseEntity<ApiResponse<ConvertResponse>> jsonToYaml(@Valid @RequestBody ConvertRequest request) {
        ConvertResponse response = converterService.jsonToYaml(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
