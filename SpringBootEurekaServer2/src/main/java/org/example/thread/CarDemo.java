package org.example.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CarDemo
 * @Description CarDemo
 * @Date 2020/4/21 9:38
 * @Author wangyong
 * @Version 1.0
 **/
public class CarDemo {
    public static void main(String[] args) {
        //创建Semaphore
        Semaphore semaphore = new Semaphore(5);
        Thread[] cars = new Thread[10];
        for (int i = 0; i < 10; i++) {
            cars[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    semaphore.acquire();
                    System.out.println(" ======== ==== = == == = == 我想要请求许可 ==== ==== == == == = == = " + Thread.currentThread().getName() + "可以进停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                    System.out.println("  ==== ==== == == == = == = " + Thread.currentThread().getName() + "离开停车场");
            },"第" + i  + "个车");
            cars[i].start();
        }
    }
}
