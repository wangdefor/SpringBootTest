package org.example.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @ClassName TestNacos
 * @Description nacos
 * @Date 2020/5/9 14:40
 * @Author wangyong
 * @Version 1.0
 **/
public class TestNacos {

    public void convert(String s, int numRows) {
        //构建一个长度为 s的二维数组
        String[][] arrays = new String[][]{{"sdad", "dsad"}, {"sda", "sdasd", "sdadsadsadsa"}};
        String[][] arrays2 = new String[s.length()][numRows];
        int count = 0;
        int index = 0;
        boolean flag = Boolean.TRUE;
        for (int i = 0; i < arrays2.length; i++) {
            for (int j = 0; j < arrays2[i].length; j++) {
                if (i % (numRows - 1) == 0) {
                    count = count + 1;
                    flag = Boolean.TRUE;
                    arrays2[i][j] = count > s.length() - 1 ? "" : s.charAt(count) + "";
                    index = numRows;
                } else {
                    if (flag) {
                        count = count + 1;
                        index = index - 1;
                        if (count < s.length() - 1) {
                            arrays2[i][index - 1] = count > s.length() - 1 ? "" : s.charAt(count) + "";
                        }
                    }
                }
            }
        }
        System.out.println(arrays[0][0]);
        System.out.println(arrays[0][1]);
        System.out.println(arrays2.length);
        System.out.println(arrays2);


    }

    public static void main(String[] args) throws NacosException {
        new TestNacos().convert("sdsdsadsadasdsadasd", 3);
    }

}
