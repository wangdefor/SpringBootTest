package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName App
 * @Date 2020/4/15 11:36
 * @Author wangyong
 * @Version 1.0
 **/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
