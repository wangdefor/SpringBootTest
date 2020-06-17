package org.example.controller;

import org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaData;
import org.example.config.ShardingSphereDataSource2;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;


    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(@RequestParam(value = "userId") Integer userId, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (dataSource instanceof ShardingSphereDataSource2) {
            ShardingSphereDataSource2 source2 = (ShardingSphereDataSource2) dataSource;
            source2.getSchemaContexts().getSchemaContexts().values().forEach(v -> {
                SchemaMetaData metaData = v.getSchema().getMetaData().getSchema().getUnconfiguredSchemaMetaDataMap().get("ds0");
                System.out.println(v.getName());
            });
        }
        return ResponseEntry.ok(userService.queryById(userId));
    }

    @GetMapping(value = "/query/order/by/id")
    public ResponseEntry queryOrderById(@RequestParam(value = "userId") Integer userId, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return ResponseEntry.ok(userService.queryOrderById(userId));
    }
}
