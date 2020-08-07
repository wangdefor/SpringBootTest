package org.example.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.model.UserModel;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName UserController
 * @Date 2020/4/14 10:40
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry getQueryById(@RequestParam(value = "userId") String userId) throws SQLException {
        log.info("------- 我是demo1，根据userId查询， userId = {}---------------------------", userId);
        return ResponseEntry.ok(Boolean.TRUE);
    }

    @PostMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(@RequestBody(required = false) UserModel userModel) {
        log.info("------- 我是demo1，userModel， userModel = {}---------------------------", JSON.toJSONString(userModel));
        return ResponseEntry.ok(Boolean.TRUE);
    }

    @GetMapping(value = "/query/user/prefix/test")
    public ResponseEntry query(@RequestParam(value = "userId") Integer userId, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        log.info("------- 我是demo1，正在测试stringPrefix， userId = {}---------------------------", userId);
        return ResponseEntry.ok(userService.queryOrderById(userId));
    }


}
