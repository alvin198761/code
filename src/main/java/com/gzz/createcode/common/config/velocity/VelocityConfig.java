package com.gzz.createcode.common.config.velocity;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class VelocityConfig {

	/**
	 * //对html文件编码的配置
	 * org.apache.velocity.app.event.implement.EscapeHtmlReference
	 * //对JavaScript文件编码的配置
	 * org.apache.velocity.app.event.implement.EscapeJavascriptReference
	 * //对sql文件编码的配置
	 * org.apache.velocity.app.event.implement.EscapeSqlReference
	 * //对xml文件编码的配置
	 * org.apache.velocity.app.event.implement.EscapeXmlReference
	 *
	 * default.contentType=text/html; charset=UTF-8
	 * input.encoding=UTF-8
	 * output.encoding=UTF-8
	 * eventhandler.referenceinsertion.class = org.apache.velocity.app.event.implement.EscapeHtmlReference
	 * eventhandler.referenceinsertion.class = org.apache.velocity.app.event.implement.EscapeSqlReference
	 * eventhandler.escape.html.match = /_html_
	 * eventhandler.escape.sql.match =/_sql_
	 * @return
	 */
	@Bean
	public VelocityEngine velocityEngine(){
		VelocityEngine ve = new VelocityEngine();

		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,"/codetemplate");
		ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
		ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
//		ve.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION ,"org.apache.velocity.app.event.implement.EscapeJavaScriptReference" );
//		ve.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION ,"org.apache.velocity.app.event.implement.EscapeHtmlReference" );
		ve.init();
		return  ve;
	}

}
