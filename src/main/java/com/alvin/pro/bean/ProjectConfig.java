package com.alvin.pro.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectConfig {

	private String name;
	private String group_name;
	private String base_package;

	private Date date;
	private String author;
}
