package com.example.pocpostgre.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Path {

    private static final Logger log = LoggerFactory.getLogger(Path.class);
    private final String jsonPath;

    private final ObjectReader objectReader = new ObjectMapper().reader();
    public Path(String path){
        this.jsonPath = path;
    }

    public String getvaluaFromJson(String json){
        JsonNode jsonNode;

        try {
            jsonNode = objectReader.readTree(json)
                    .at(formatedPath(this.jsonPath));
        } catch (JsonProcessingException e) {
            log.error("Erro ao ler json. message={}", e.getMessage());
            return null;
        }

        if (!jsonNode.isValueNode()){
            return null;
        }

        return jsonNode.asText();
    }

    private String formatedPath(String path){
        return "/" + path.replace(".", "/");
    }

    public String getPath() {
        return this.jsonPath;
    }
}
