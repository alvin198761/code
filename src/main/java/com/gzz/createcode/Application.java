package com.gzz.createcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Autowired
//	protected FreeMarkerViewResolver resolver;
//
//	@PostConstruct
//	public void setSharedVariable() {
//		resolver.setSuffix(".vue");
//	}
}
