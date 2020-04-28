package org.example.controller;

import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(@RequestParam(value = "userId") Integer userId) {
        return ResponseEntry.ok(userService.queryById(userId));
    }

}
