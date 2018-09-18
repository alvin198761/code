package com.alvin.mock.bean;

import lombok.Data;

import java.util.Map;

@Data
public class JSMockFunction {

    private String name;
    private String url;
    private String method;
    private String description;
    private String header;
    private Map<String, Object> params;
    private Map<String, Object> response;

}
