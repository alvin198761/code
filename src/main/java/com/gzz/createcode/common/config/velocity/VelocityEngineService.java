package com.gzz.createcode.common.config.velocity;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;

@Component
public class VelocityEngineService {

	@Autowired
	private VelocityEngine velocityEngine;

	public void parse(String templateName, Object obj, String outPath) {
		Template t = velocityEngine.getTemplate(templateName);
		t.setEncoding("utf-8");
		VelocityContext ctx = new VelocityContext();
		JSONObject.parseObject(JSONObject.toJSONString(obj)).forEach(ctx::put);
		try (Writer writer = Files.newWriter(new File(outPath), Charset.forName("utf-8"))) {
			t.merge(ctx, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
