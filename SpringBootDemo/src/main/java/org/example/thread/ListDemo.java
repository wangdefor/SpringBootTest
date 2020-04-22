package org.example.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * @ClassName ListDemo
 * @Description demo
 * @Date 2020/4/21 10:08
 * @Author wangyong
 * @Version 1.0
 **/
public class ListDemo {



    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            listIterator.remove();
        }
    }
}
