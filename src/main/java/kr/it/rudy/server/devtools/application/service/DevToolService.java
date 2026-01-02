package kr.it.rudy.server.devtools.application.service;

import kr.it.rudy.server.common.dto.Base64Response;
import kr.it.rudy.server.common.dto.JsonParserResponse;
import kr.it.rudy.server.common.dto.UrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class DevToolService {
    private final ObjectMapper objectMapper;

    public JsonParserResponse jsonParser(String json, int indentSize) {
        try {
            // JSON 유효성 검사 및 파싱
            Object parsedJson = objectMapper.readValue(json, Object.class);

            // 포맷팅된 JSON 생성
            String indent = " ".repeat(Math.max(0, indentSize));

            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            printer.indentObjectsWith(new DefaultIndenter(indent, "\n"));
            printer.indentArraysWith(new DefaultIndenter(indent, "\n"));

            String formattedJson = objectMapper.writer()
                    .with(printer)
                    .writeValueAsString(parsedJson);

            // 최소화된 JSON 생성
            String minifiedJson = objectMapper.writeValueAsString(parsedJson);

            return new JsonParserResponse(
                    formattedJson,
                    minifiedJson,
                    true,
                    null
            );
        } catch (Exception e) {
            return new JsonParserResponse(
                    null,
                    null,
                    false,
                    e.getMessage()
            );
        }
    }

    public Base64Response encodeBase64(String text) {
        try {
            if (text == null || text.isEmpty()) {
                return new Base64Response(null, false, "입력 텍스트가 비어있습니다.");
            }
            String encoded = Base64.getEncoder().encodeToString(text.getBytes());
            return new Base64Response(encoded, true, null);
        } catch (Exception e) {
            return new Base64Response(null, false, e.getMessage());
        }
    }

    public Base64Response decodeBase64(String text) {
        try {
            if (text == null || text.isEmpty()) {
                return new Base64Response(null, false, "입력 텍스트가 비어있습니다.");
            }
            byte[] decodedBytes = Base64.getDecoder().decode(text);
            String decoded = new String(decodedBytes);
            return new Base64Response(decoded, true, null);
        } catch (IllegalArgumentException e) {
            return new Base64Response(null, false, "유효하지 않은 Base64 문자열입니다.");
        } catch (Exception e) {
            return new Base64Response(null, false, e.getMessage());
        }
    }

    public UrlResponse encodeUrl(String text) {
        try {
            if (text == null || text.isEmpty()) {
                return new UrlResponse(null, false, "입력 텍스트가 비어있습니다.");
            }
            String encoded = URLEncoder.encode(text, StandardCharsets.UTF_8);
            return new UrlResponse(encoded, true, null);
        } catch (Exception e) {
            return new UrlResponse(null, false, e.getMessage());
        }
    }

    public UrlResponse decodeUrl(String text) {
        try {
            if (text == null || text.isEmpty()) {
                return new UrlResponse(null, false, "입력 텍스트가 비어있습니다.");
            }
            String decoded = URLDecoder.decode(text, StandardCharsets.UTF_8);
            return new UrlResponse(decoded, true, null);
        } catch (IllegalArgumentException e) {
            return new UrlResponse(null, false, "유효하지 않은 URL 인코딩 문자열입니다.");
        } catch (Exception e) {
            return new UrlResponse(null, false, e.getMessage());
        }
    }
}
