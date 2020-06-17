package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.OrderModel;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    List<OrderModel> queryById(Integer userId);
}
