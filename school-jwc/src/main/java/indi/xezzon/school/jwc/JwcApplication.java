package indi.xezzon.school.jwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xezzon
 * 教务管理系统启动类
 */
@SpringBootApplication
@EnableEurekaClient
public class JwcApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwcApplication.class, args);
    }
}
