package indi.xezzon.school.jwc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xezzon
 * 教务管理系统启动类
 */
@SpringBootApplication
@MapperScan("indi.xezzon.school.jwc.repository")
public class JwcApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwcApplication.class, args);
    }
}
