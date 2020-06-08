package com.william.bc_mall_server;

import com.william.bc_mall_server.config.MyDateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class BcMallServerApplication implements WebMvcConfigurer {

	@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}

	/**
	 * 静态资源映射
	 * @param registry
	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// 当访问/file下的资源时，会到/root/myFile/下去找
//		// 例如访问：http://localhost:8080/file/1.png时会去找/root/myFile/1.png
//		registry.addResourceHandler("/file/**").addResourceLocations("file:/root/myFile/");
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.maxAge(3600)
				.allowCredentials(true);
	}


	@Bean
	public Converter<String, Date> addNewConvert() {
		return new MyDateConvert() ;
	}

	public static void main(String[] args) {
		SpringApplication.run(BcMallServerApplication.class, args);
	}

}
