package org.example.model;

/**
 * @ClassName OrderModel
 * @Description TODO
 * @Date 2020/6/17 15:15
 * @Author wangyong
 * @Version 1.0
 **/
public class OrderModel {

    private int orderId;

    private int userId;


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }
}
