package top.gwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 扫描过滤器、Servlet等组件
public class FilterInterceptorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterInterceptorApplication.class, args);
    }
}