package org.example.service;

import org.example.model.UserModel;

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
     * @param userId
     * @return
     */
    UserModel queryById(Integer userId);

}
