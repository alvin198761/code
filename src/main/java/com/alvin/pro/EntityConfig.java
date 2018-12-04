package com.alvin.pro;

import lombok.Data;

import java.util.List;

/**
 * 实体类配置
 */
@Data
public class EntityConfig {

	//类名
	private String name;
	//id 属性名称
	private String idName;
	//显示文本的属性名称
	private String labalName;
	//表名称
	private String table_name;
	//作者
	private String author;
	//备注
	private String remark;
	//创建日期
	private String date;
	//所有属性
	private List<FieldConfig> fields;

	//-----------
	private String projectName; //添加类时候用

}
