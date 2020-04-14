package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.model.UserModel;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public UserModel queryById(Integer userId) {
        return userMapper.queryById(userId);
    }
}
