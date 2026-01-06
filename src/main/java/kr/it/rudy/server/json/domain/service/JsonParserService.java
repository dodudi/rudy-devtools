package kr.it.rudy.server.json.domain.service;

import kr.it.rudy.server.json.application.dto.JsonParserResponse;
import kr.it.rudy.server.json.application.service.JsonParserProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonParserService {
    private final JsonParserProcessor jsonParserProcessor;

    public JsonParserResponse jsonParser(String json, int indentSize) {
        String formattedJson = jsonParserProcessor.formattedJson(json, indentSize);
        String minifiedJson = jsonParserProcessor.minifiedJson(json);
        return new JsonParserResponse(formattedJson, minifiedJson, true);
    }
}
