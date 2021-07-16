package pl.javastart.restassured.main.pojo;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    private Integer code;
    private String type;
    private String message;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public ApiResponse(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public ApiResponse(){
    };


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
