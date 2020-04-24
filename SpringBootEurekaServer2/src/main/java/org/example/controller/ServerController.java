package org.example.controller;

import org.example.common.ResponseModel;
import org.example.model.UserModel;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerController
 * @Description 通过eureka来作为服务端给客户端调用调用
 * @Date 2020/4/24 10:17
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
public class ServerController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/server/query/by/id")
    public ResponseModel<UserModel> queryById(@RequestParam(value = "id") Integer id){
        System.out.println("server2");
        return ResponseModel.ok(userService.queryById(id));
    }

}
