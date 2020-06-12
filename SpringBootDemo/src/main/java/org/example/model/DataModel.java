package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName DataModel
 * @Description TODO
 * @Date 2020/6/12 16:15
 * @Author wangyong
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataModel {

    private String url;

    private String username;

    private String password;

    /**
     * 别名
     */
    private String alias;
}
