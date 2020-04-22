package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName App
 * @Description App
 * @Date 2020/4/14 10:47
 * @Author wangYong
 * @Version 1.0
 **/
@EnableDiscoveryClient
@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class App {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
