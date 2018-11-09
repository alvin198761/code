package com.alvin.mock.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ActionMethodBean {

	private String method;//方法
	private String url;//地址
	private String note;//说明
	private String description;//注释

	private JSONObject headers; //请求头
	private JSONObject params; //参数
	private JSONObject response; //响应结构

	private String reqParamType;//请求参数方式   body formdata
	private List<Map<Integer, String>> responseMsg;//响应错误信息

	private String paramsStr;//显示的参数拼装结果

}
