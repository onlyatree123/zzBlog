package com.zz.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages="com.zz.blog")   //默认扫描是当前包下的路径
@EnableAutoConfiguration
public class BlogApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlogApplication.class, args);
	}
}
