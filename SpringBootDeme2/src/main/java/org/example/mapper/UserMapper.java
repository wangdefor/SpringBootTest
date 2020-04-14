package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.UserModel;

@Mapper
public interface UserMapper {
    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    UserModel queryById(Integer userId);
}
