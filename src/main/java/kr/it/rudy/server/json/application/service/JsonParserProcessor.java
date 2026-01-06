package kr.it.rudy.server.json.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class JsonParserProcessor {

    private final ObjectMapper objectMapper;

    public String formattedJson(String json, int indentSize) {
        if (!StringUtils.hasText(json)) {
            throw new IllegalArgumentException("json must not be empty or null");
        }

        Object parsedJson = objectMapper.readValue(json, Object.class);
        String indent = " ".repeat(Math.max(0, indentSize));

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(new DefaultIndenter(indent, "\n"));
        printer.indentArraysWith(new DefaultIndenter(indent, "\n"));

        return objectMapper.writer()
                .with(printer)
                .writeValueAsString(parsedJson);
    }

    public String minifiedJson(String json) {
        if (!StringUtils.hasText(json)) {
            throw new IllegalArgumentException("json must not be empty or null");
        }

        Object parsedJson = objectMapper.readValue(json, Object.class);
        return objectMapper.writeValueAsString(parsedJson);
    }
}
