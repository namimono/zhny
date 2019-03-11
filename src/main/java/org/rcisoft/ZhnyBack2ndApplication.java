package org.rcisoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableSwagger2
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages={"org.rcisoft"})
@MapperScan(basePackages = {"org.rcisoft.**.dao"})
public class ZhnyBack2ndApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(ZhnyBack2ndApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ZhnyBack2ndApplication.class, args);
	}
}
