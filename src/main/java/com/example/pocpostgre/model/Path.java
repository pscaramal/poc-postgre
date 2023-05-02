package com.example.pocpostgre.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Path {

    private static final Logger log = LoggerFactory.getLogger(Path.class);
    private final String jsonPath;

    private final ObjectReader objectReader = new ObjectMapper().reader();
    public Path(String path){
        this.jsonPath = path;
    }

    public String getValueFromJson(String json){
        JsonNode jsonNode;

        var formattedPath = formattedPath(this.jsonPath);
        List<String> jsonPathWithArrays = splittingPathInArray(formattedPath);

        try {
            jsonNode = objectReader.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("Erro ao ler json. message={}", e.getMessage());
            return null;
        }

        var finalNode = processJson(jsonNode, jsonPathWithArrays);

        return (finalNode == null || !finalNode.isValueNode())? null : finalNode.asText();
    }

    private JsonNode processJson(JsonNode jsonNode, List<String> jsonPathInArray) {
        int index = 0;
        JsonNode node = jsonNode.at(jsonPathInArray.get(index));

        if(node.isArray()){
            return processNodeJsonArray(node, jsonPathInArray, ++index);
        }
        return node;
    }

    private JsonNode processNodeJsonArray(JsonNode node, List<String> jsonPathInArray, int index) {
        JsonNode newNodeItem = null;
        for(JsonNode nodeItem : node){
            if (nodeItem.at(jsonPathInArray.get(index)).isValueNode() || nodeItem.at(jsonPathInArray.get(index)).isArray()){
                newNodeItem = nodeItem.at(jsonPathInArray.get(index));
                break;
            }
        }

        if (newNodeItem == null || newNodeItem.isValueNode() || index == jsonPathInArray.size() - 1){
            if (newNodeItem == null){
                return null;
            }

            if (newNodeItem.isValueNode() && index < jsonPathInArray.size() - 1) {
                //O path foi cadastradado incorretamente
                return null;
            }
            return newNodeItem;
        }

        return processNodeJsonArray(newNodeItem, jsonPathInArray, ++index);
    }

    private String formattedPath(String path){
        return "/" + path.replace(".", "/");
    }

    private List<String> splittingPathInArray(String paths) {
        return Arrays.stream(paths.split("\\[]")).toList();
    }

    public String getPath() {
        return this.jsonPath;
    }
}
