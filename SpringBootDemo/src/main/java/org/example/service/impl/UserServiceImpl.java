package org.example.service.impl;

import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.example.model.OrderModel;
import org.example.model.UserModel;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description userService
 * @Date 2020/4/14 11:03
 * @Author wangyong
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public UserModel queryById(Integer userId) {
        return userMapper.queryById(userId);
    }

    @Override
    public List<OrderModel> queryOrderById(Integer userId) {
        return orderMapper.queryById(userId);
    }
}
