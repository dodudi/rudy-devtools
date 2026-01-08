package kr.it.rudy.server.converter.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.stereotype.Component;

@Component
public class FormatConverter {

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();
    private final YAMLMapper yamlMapper = new YAMLMapper();

    public String jsonToXml(String json, int indent) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON content must not be empty");
        }

        try {
            JsonNode jsonNode = jsonMapper.readTree(json);

            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (indent > 0) {
                xmlMapper.writer().withDefaultPrettyPrinter();
            }

            return xmlMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert JSON to XML: " + e.getMessage(), e);
        }
    }

    public String xmlToJson(String xml, int indent) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new IllegalArgumentException("XML content must not be empty");
        }

        try {
            JsonNode xmlNode = xmlMapper.readTree(xml);

            if (indent > 0) {
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
                return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(xmlNode);
            } else {
                return jsonMapper.writeValueAsString(xmlNode);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert XML to JSON: " + e.getMessage(), e);
        }
    }

    public String yamlToJson(String yaml, int indent) {
        if (yaml == null || yaml.trim().isEmpty()) {
            throw new IllegalArgumentException("YAML content must not be empty");
        }

        try {
            Object yamlObject = yamlMapper.readValue(yaml, Object.class);

            if (indent > 0) {
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
                return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(yamlObject);
            } else {
                return jsonMapper.writeValueAsString(yamlObject);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert YAML to JSON: " + e.getMessage(), e);
        }
    }

    public String jsonToYaml(String json, int indent) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON content must not be empty");
        }

        try {
            Object jsonObject = jsonMapper.readValue(json, Object.class);
            return yamlMapper.writeValueAsString(jsonObject);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert JSON to YAML: " + e.getMessage(), e);
        }
    }
}
