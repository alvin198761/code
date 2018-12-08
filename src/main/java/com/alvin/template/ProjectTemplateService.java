package com.alvin.template;

import com.alibaba.fastjson.JSONObject;
import com.alvin.mock.utils.ZipUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目模板 业务处理
 */
@Service
public class ProjectTemplateService {

	@Value("${gen.project.templatedir}")
	private String templateDir;

	/**
	 * 保存项目
	 *
	 * @param templateConfig
	 * @return
	 */
	public int save(ProjectTemplateConfig templateConfig) {
		File templateDir = new File(this.templateDir, templateConfig.getName());
		templateDir.mkdirs();
		try {
			Files.write(Paths.get(templateDir.getAbsolutePath(), templateConfig.getName()), JSONObject.toJSONBytes(templateConfig));
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 接收zip 上传
	 *
	 * @param multipartFile
	 * @return
	 */
	public int uploadTemplate(MultipartFile multipartFile) {
//		ZipUtils.do
	}


	public ProjectTemplateConfig getTemplateByName(String templateName) throws IOException {
		return JSONObject.parseObject(Files.readAllBytes(Paths.get(new File(templateDir, templateName + "/config.json").toURI())), ProjectTemplateConfig.class);
	}

	/**
	 * 添加文件
	 *
	 * @param file
	 * @return
	 */
	public int addFile(ProjectTemplateFile file) {
//		1 普通模板 2 实体模板 3 非模板 4 目录
		File templateDir = new File(this.templateDir, file.getTemplateName());
		if (file.getType() == 4) {
			new File(templateDir, file.getPath()).mkdirs();
			try {
				ProjectTemplateConfig projectTemplateConfig = getTemplateByName(file.getTemplateName());
				projectTemplateConfig.getTemplateFiles().add(file);
				file.setTemplateName(null);
				save(projectTemplateConfig);
				return 1;
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
		try {
			Files.write(Paths.get(templateDir.getAbsolutePath(), file.getPath()), file.getContent().getBytes("utf-8"));
			ProjectTemplateConfig projectTemplateConfig = getTemplateByName(file.getTemplateName());
			projectTemplateConfig.getTemplateFiles().add(file);
			file.setContent(null);
			file.setTemplateName(null);
			save(projectTemplateConfig);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 上传文件
	 *
	 * @param part
	 * @param file
	 * @return
	 */
	public int uploadFile(MultipartFile part, ProjectTemplateFile file) {
		File templateDir = new File(this.templateDir, file.getTemplateName());
		try {
			Files.write(Paths.get(templateDir.getAbsolutePath(), file.getPath()), part.getBytes());
			ProjectTemplateConfig projectTemplateConfig = getTemplateByName(file.getTemplateName());
			projectTemplateConfig.getTemplateFiles().add(file);
			file.setContent(null);
			file.setTemplateName(null);
			save(projectTemplateConfig);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 列表显示文件
	 *
	 * @return
	 */
	public List<ProjectTemplateConfig> list() {
		File file = new File(templateDir);
		if (!file.exists()) {
			file.mkdirs();
			return Lists.newArrayList();
		}
		File[] files = file.listFiles();
		if (files == null) {
			return Lists.newArrayList();
		}
		return Lists.newArrayList(files).stream().filter(item -> {
			//判断是否有固定的文件
			File configFile = new File(item, "config.json");
			return configFile.exists();
		}).map(item -> {
			try {
				return JSONObject.parseObject(Files.readAllBytes(Paths.get(new File(item, "config.json").toURI())), ProjectTemplateConfig.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return (ProjectTemplateConfig) null;
		}).collect(Collectors.toList());
	}
}
