package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
//import org.example.annotations.UserAuthorization;
import org.example.model.UserModel;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.MacSigner;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

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

    private static int count = 0;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(
            @RequestParam(value = "userId") Integer userId) {
        log.info("------- 我是demo2，根据userId查询， userId = {}---------------------------", userId);
        return ResponseEntry.ok(userService.queryById(userId));
    }

    @PostMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(@RequestBody(required = false) UserModel userModel) {
        log.info("------- 我是demo2，userModel， userModel = {}---------------------------", JSON.toJSONString(userModel));
        return ResponseEntry.ok(Boolean.TRUE);
    }


    @GetMapping(value = "/weight/query/user/by/query")
    public ResponseEntry weightQuery(@RequestParam(value = "userId") Integer userId) throws SQLException {
        count = count + 1;
        log.info("------- 我是demo1，正在测试predicate weight, 调用次数为{} ， userId = {}---------------------------", count,userId);
        return ResponseEntry.ok(Boolean.TRUE);
    }
}
