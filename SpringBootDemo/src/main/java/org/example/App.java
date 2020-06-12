package org.example;

import com.purgeteam.dynamic.config.starter.annotation.EnableDynamicConfigEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName App
 * @Description App
 * @Date 2020/4/14 10:47
 * @Author wangYong
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableDynamicConfigEvent
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
