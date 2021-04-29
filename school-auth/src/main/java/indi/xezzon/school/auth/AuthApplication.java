package indi.xezzon.school.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xezzon
 */
@SpringBootApplication(scanBasePackages = "indi.xezzon.school")
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("indi.xezzon.school.auth.repository")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
