package org.example.controller;

import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.example.common.ResponseModel;
import org.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
@Slf4j
public class ClientController {

    private static final String SERVICE_URL = "http://Spring-Boot-Client";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/client/query/by/id")
    @ApiOperation(value = "获取用户id", notes = "获取用户id")
    public ResponseModel<UserModel> queryById(@ApiParam(value = "用户id",name = "id") @RequestParam(value = "id") Integer id){
        System.out.println(id);
        List<ServiceInstance> instances = discoveryClient.getInstances("Spring-Boot-Client");
        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance instance = instances.get(i);
            log.info("host地址为：" + instance.getHost() + ",host的端口为：" + instance.getPort());
        }
        return restTemplate.getForEntity(SERVICE_URL + "/server/query/by/id?id=" + id,ResponseModel.class).getBody();
    }

}
