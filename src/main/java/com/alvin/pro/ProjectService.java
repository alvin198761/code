package com.alvin.pro;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ProjectService {

	/**
	 * 生成项目，返回生成后的路径
	 *
	 * @param projectConfig
	 * @return
	 */
	public String gen(ProjectConfig projectConfig) {
		//先完成死规则的
		File templatesDir = new File(System.getProperty("user.dir"), "project_templates");
		File distDir = new File(System.getProperty("user.dir"), "out_dist");
		//
		initProject(distDir, projectConfig);
		return "";
	}

	private void initProject(File distDir, ProjectConfig projectConfig) {
		File file = new File(distDir, projectConfig.getName());
		file.mkdirs();
		File srcFile = new File(file, "src");
		srcFile.mkdirs();
//		velocityEngineService.parse();
	}
}
