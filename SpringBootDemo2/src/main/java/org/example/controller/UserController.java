package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.example.annotations.UserAuthorization;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

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


    @Value("${test.value}")
    private String value;

    @Value("${ms.authentication.service.domain}")
    private String value2;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(
            @RequestHeader("Authorization") String tk,
            @RequestParam(value = "userId") Integer userId) {
        OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(tk.split(" ")[1]);
        Map<String, Object> information = oAuth2AccessToken.getAdditionalInformation();
        System.out.println(information);
        log.info(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        log.info(value + useLocalCache + value2);
        return ResponseEntry.ok(userService.queryById(userId));
    }

}
