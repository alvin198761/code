package com.alvin.pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/project")
public class ProjectAction {
	@Autowired
	private ProjectService projectService;

	@PostMapping("gen")
	public String gen(@RequestBody ProjectConfig projectConfig) {
		System.out.println(projectConfig.getName());
		projectConfig.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mmLss").format(new Date()));
		return this.projectService.gen(projectConfig);
	}
}
