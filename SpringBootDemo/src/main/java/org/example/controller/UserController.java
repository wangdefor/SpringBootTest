package org.example.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shardingsphere.sharding.strategy.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.example.config.ShardingSphereDataSource2;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

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
        Connection conn = dataSource.getConnection();
        int userIds = RandomUtils.nextInt();
        System.out.println(userIds);
        //oder_id 由雪花算法生成唯一主键
        String sql = "INSERT INTO t_order (user_id,category_id,board_name,layout_json)VALUES (" + userId + ",1,1,1)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        System.out.println(2 % 3);
//        String sql2 = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.order_id in (10, 11)";
//        PreparedStatement ps2 = conn.prepareStatement(sql2);
//        ResultSet rs = ps2.executeQuery();
//        while (rs.next()){
//            System.out.println("-----------------------------------" + rs.getInt(0) + "----------------------------");
//        }
        if (conn != null) {
            conn.close();
        }
        if (ps != null) {
            ps.close();
        }
        return ResponseEntry.ok(userService.queryById(userId));
    }

    @GetMapping(value = "/add/source")
    public void addDataSource() throws SQLException {

        if (dataSource instanceof ShardingSphereDataSource2) {
            ShardingSphereDataSource2 source2 = (ShardingSphereDataSource2) dataSource;
            // 配置第 2 个数据源
            DruidDataSource dataSource2 = new DruidDataSource();
            dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource2.setUrl("jdbc:mysql://10.0.2.8:3306/ways?characterEncoding=utf-8&amp;allowMultiQueries=true");
            dataSource2.setUsername("cimendev");
            dataSource2.setPassword("123456");
            source2.addDataSource("ds1", dataSource2);
            dataSource = source2;
        }
    }
}
