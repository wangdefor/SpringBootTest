package org.example.controller;

import org.example.response.ResponseEntry;
import org.example.service.HystrixTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HystrixServerController
 * @Description hystrixTestService
 * @Date 2020/4/27 10:02
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
public class HystrixServerController {

    @Autowired
    private HystrixTestService hystrixTestService;

    @GetMapping("/test/hystrix/id")
    public ResponseEntry<String> testHystrix(@RequestParam(value = "id") Integer id) throws InterruptedException {
        return ResponseEntry.ok(hystrixTestService.hello(id));
    }


}
