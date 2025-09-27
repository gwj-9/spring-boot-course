package top.gwj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        // 启动 Spring Boot 应用，加载所有注解和配置
        SpringApplication.run(RedisApplication.class, args);
    }
}