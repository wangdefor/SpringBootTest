package org.example.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.example.model.UserModel;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ServerController
 * @Description 通过eureka来作为服务端给客户端调用调用
 * @Date 2020/4/24 10:17
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
@Slf4j
public class ServerController {

    @NacosValue("${test.value}")
    private String testValue;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/server/query/by/id")
    public ResponseEntry<UserModel> queryById(@RequestParam(value = "id") Integer id) throws InterruptedException {
        log.info(testValue);
        System.out.println("server1");
        TimeUnit.SECONDS.sleep(15);
        return ResponseEntry.ok(userService.queryById(2));
    }

}
