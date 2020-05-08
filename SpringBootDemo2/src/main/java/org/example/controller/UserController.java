package org.example.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Date 2020/4/14 10:40
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
@Slf4j
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;


    @Value("${test.value}")
    private String value;

    @Value("${ms.authentication.service.domain}")
    private String value2;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(@RequestParam(value = "userId") Integer userId) {
        log.info(value + useLocalCache + value2);
        return ResponseEntry.ok(userService.queryById(userId));
    }

}
