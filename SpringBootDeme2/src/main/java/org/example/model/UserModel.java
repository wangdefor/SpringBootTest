package org.example.model;

import java.io.Serializable;

/**
 * @ClassName UserModel
 * @Description UserModel
 * @Date 2020/4/14 10:36
 * @Author wangyong
 * @Version 1.0
 **/

public class UserModel implements Serializable {


    public UserModel(){
        super();
    }

    private String userName;

    private Integer userId;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
