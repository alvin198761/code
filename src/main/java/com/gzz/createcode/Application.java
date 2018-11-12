package com.gzz.createcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
		@ComponentScan("com.gzz.*"),
		@ComponentScan("com.alvin.*")
})
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
