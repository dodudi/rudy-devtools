package kr.it.rudy.server.json.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class JsonParserProcessor {

    private final ObjectMapper objectMapper;

    public String formattedJson(String json, int indentSize) {
        Object parsedJson = parsedJson(json);

        // 포맷팅된 JSON 생성
        String indent = " ".repeat(Math.max(0, indentSize));

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(new DefaultIndenter(indent, "\n"));
        printer.indentArraysWith(new DefaultIndenter(indent, "\n"));

        return objectMapper.writer()
                .with(printer)
                .writeValueAsString(parsedJson);
    }

    public String minifiedJson(String json) {
        Object parsedJson = parsedJson(json);
        return objectMapper.writeValueAsString(parsedJson);
    }

    private Object parsedJson(String json) {
        return objectMapper.readValue(json, Object.class);
    }

}
