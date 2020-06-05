package indi.xezzon.school.passport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 *
 * @author xezzon
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan ("indi.xezzon.school.passport.repository")
public class PassportApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PassportApplication.class);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PassportApplication.class);
    }
    
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
        
        };
    }
}