package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HystrixTestService
 * @Description 豪猪测试
 * @Date 2020/4/27 9:39
 * @Author wangyong
 * @Version 1.0
 **/
@Service
@DefaultProperties(defaultFallback = "fallback")
public class HystrixTestService {


    public String hello(Integer id) {
        int j = 1 / id;
        String meessage = "hi boys or girls ，welcome to my method, name is " + Thread.currentThread().getName();
        System.out.println(meessage);
        return meessage;
    }


    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3")}
    )
    public String timeOutHello(Integer id) throws InterruptedException {
        int j = 1 / id;
        String message = "hi boy or girl ，welcome to my method, name is " + Thread.currentThread().getName();
        System.out.println(message);
        return message;
    }

    public String fallback(Integer id) throws InterruptedException {
        String meessage = "I am a bad boy or girl ，welcome to my method, name is " + Thread.currentThread().getName();
        return meessage;
    }
}
