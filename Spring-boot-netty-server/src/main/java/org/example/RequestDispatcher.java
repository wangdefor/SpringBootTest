package org.example;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @ClassName RequestDispatcher
 * @Description TODO
 * @Date 2020/7/7 17:35
 * @Author wangyong
 * @Version 1.0
 **/
@Component
public class RequestDispatcher implements ApplicationContextAware {

//    ExecutorService pool = new ThreadPoolExecutor(5, 200,
//            0L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>(1024), "wy", new ThreadPoolExecutor.AbortPolicy());

    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private ApplicationContext app;

    /**
     * 发送
     *
     * @param ctx
     */
    public void dispatcher(final ChannelHandlerContext ctx) {
        executorService.submit(() -> {
            System.out.println("执行");
        });
    }

    /**
     * 加载当前application.xml
     *
     * @param ctx
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.app = ctx;
    }
}
