package indi.xezzon.school.jwc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xezzon
 * 教务管理系统启动类
 */
@SpringBootApplication(scanBasePackages = "indi.xezzon.school")
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("indi.xezzon.school.jwc.repository")
public class JwcApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwcApplication.class, args);
    }
}
