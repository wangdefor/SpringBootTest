package org.example.service;

import org.example.model.OrderModel;
import org.example.model.UserModel;

import java.util.List;

/**
 * @ClassName UserService
 * @Description UserService 用户接口类
 * @Date 2020/4/14 11:02
 * @Author wangyong
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 根据ID查询用户信息
     *
     * @param userId
     * @return
     */
    UserModel queryById(Integer userId);

    /**
     * 查询订单模板
     *
     * @param orderId
     * @return
     */
    List<OrderModel> queryOrderById(Integer orderId);

}
