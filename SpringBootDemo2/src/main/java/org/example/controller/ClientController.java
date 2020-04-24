package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.example.common.ResponseModel;
import org.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ClientController
 * @Description ClientController
 * @Date 2020/4/24 10:26
 * @Author wangyong
 * @Version 1.0
 **/
@ResponseBody
@RestController
@Api(tags = {"client测试"}, protocols = "http")
public class ClientController {

    private static final String SERVICE_URL = "http://Spring-Boot-Client";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/client/query/by/id")
    @ApiOperation(value = "获取用户id", notes = "获取用户id")
    public ResponseModel<UserModel> queryById(@ApiParam(value = "用户id",name = "id") @RequestParam(value = "id") Integer id){
        System.out.println(id);
        return restTemplate.getForEntity(SERVICE_URL + "/server/query/by/id?id=" + id,ResponseModel.class).getBody();
    }

}
