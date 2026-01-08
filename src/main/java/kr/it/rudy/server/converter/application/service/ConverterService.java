package kr.it.rudy.server.converter.application.service;

import kr.it.rudy.server.converter.application.dto.ConvertRequest;
import kr.it.rudy.server.converter.application.dto.ConvertResponse;
import kr.it.rudy.server.converter.domain.service.FormatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final FormatConverter formatConverter;

    public ConvertResponse jsonToXml(ConvertRequest request) {
        String result = formatConverter.jsonToXml(request.content(), request.indent());
        return new ConvertResponse(result);
    }

    public ConvertResponse xmlToJson(ConvertRequest request) {
        String result = formatConverter.xmlToJson(request.content(), request.indent());
        return new ConvertResponse(result);
    }

    public ConvertResponse yamlToJson(ConvertRequest request) {
        String result = formatConverter.yamlToJson(request.content(), request.indent());
        return new ConvertResponse(result);
    }

    public ConvertResponse jsonToYaml(ConvertRequest request) {
        String result = formatConverter.jsonToYaml(request.content(), request.indent());
        return new ConvertResponse(result);
    }
}
