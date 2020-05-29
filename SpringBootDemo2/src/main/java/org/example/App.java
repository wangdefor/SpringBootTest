package org.example;

import org.example.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @ClassName App
 * @Description TODO
 * @Date 2020/4/14 10:47
 * @Author wangyong
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
@Import({
        ApplicationConfig.class,  // 引入 Swagger2 接口文档依赖
})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
//@EnableResourceServer
//@EnableOAuth2Client
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
