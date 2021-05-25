package indi.xezzon.school.eams;

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
@MapperScan("indi.xezzon.school.eams.repository")
public class EamsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EamsApplication.class, args);
    }
}
