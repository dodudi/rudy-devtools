package kr.it.rudy.server.devtools.presentation.controller;

import kr.it.rudy.server.devtools.application.service.DevToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devtools")
public class DevToolController {
    private final DevToolService devToolService;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
