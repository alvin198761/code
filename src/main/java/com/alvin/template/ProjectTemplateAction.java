package com.alvin.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目模板业务
 */
@RestController
@RequestMapping("/api/template")
public class ProjectTemplateAction {

	@Autowired
	private ProjectTemplateService service;

	@RequestMapping("save")
	public int save(@RequestBody ProjectTemplateConfig templateConfig){
		return this.service.save(templateConfig);
	}

	@RequestMapping("list")
	public List<ProjectTemplateConfig> list(){
		return this.service.list();
	}
}
