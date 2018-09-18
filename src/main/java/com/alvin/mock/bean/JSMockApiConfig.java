package com.alvin.mock.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class JSMockApiConfig {

    private String url;
    private String token;
    private List<JSONObject> tags;
}
