package com.alvin.template;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 项目模板
 */
@Data
public class ProjectTemplateConfig {

	//项目模板名称
	private String name;
	//后端源码根目录
	private String back_src_dir;
	//前端源码根目录
	private String front_src_dir;
	//作者
	private String author;
	//创建日期
	private String date;
	//模板位置
	private String path;
	//模板文件列表
	private List<ProjectTemplateFile> templateFiles = Lists.newArrayList();

}
