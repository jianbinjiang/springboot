package com.hk.springboot;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author jiangjianbin
 * 
 */
/*Restful API 访问路径:  
    * http://IP:port/{context-path}/swagger-ui.html  
    * eg:http://localhost:8080/jd-config-web/swagger-ui.html  
*//*
 * Spring Boot最大的特色是“约定优先配置”，大量的默认配置对开发者十分的友好。
 * 但是在实际的应用开发过程中，默认配置不可能满足所有场景，同时用户也需要配置一些必须的配置项——例如数据库连接信息。
 * Spring Boot的配置系统能够让开发者快速的覆盖默认约定，同时支持Properties配置文件和YAML配置文件两种格式，
 * 默认情况下Spring Boot加载类路径上的application.properties或application.yml文件
 */
@EnableAutoConfiguration
@Controller
@EnableSwagger2
@ComponentScan("com.hk.springboot")
public class App {
    public static void main( String[] args ){
       SpringApplication.run(App.class, args);
    }
    //设置tomact的属性
    @Bean
	public EmbeddedServletContainerFactory servletContainer(){
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(9999);
		return factory;
	}
    @Bean
    public Docket myApi(){
    	return new Docket(DocumentationType.SWAGGER_2).select() //生成Docket的选择器对API进行筛选
    			.apis(RequestHandlerSelectors.any())//针对controller进行筛选
    			.paths(PathSelectors.any())//针对路径进行筛选
    			.build()
    			.pathMapping("/")// API的根目录
    			.directModelSubstitute(Date.class, String.class)// 输出模型的替换(把日期以字符串的形式输出)
    			.genericModelSubstitutes(ResponseEntity.class) //针对泛型的处理(指定其泛型是哪种类型)
    			.apiInfo(new ApiInfoBuilder()
    					.title("Spring 中使用Swagger2构建RESTful APIs")
    					.description("ssssssssssssssssss")
    					.contact("jiangjianbin开发组")
    					.version("2.0")
    					.build());
    }
}
