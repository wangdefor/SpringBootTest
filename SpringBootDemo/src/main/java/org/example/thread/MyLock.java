package org.example.thread;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @ClassName MyLock
 * @Description lock  锁复习
 * @Date 2020/4/20 9:32
 * @Author wangyong
 * @Version 1.0
 **/
public class MyLock implements Lock {

    private HelpLock helpLock = new HelpLock();

    private class HelpLock extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if(state == 0){
                //利用cas原理修改state
                if(compareAndSetState(0, arg)){
                    //设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                //可重入锁判断，同一个锁对多个资源进行占有的时候，直接分配给这个线程
            }else if(getExclusiveOwnerThread().equals(Thread.currentThread())){
                setState(state + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int status = getState() - arg;
            boolean flag = Boolean.FALSE;
            //判断释放后为0
            if(status == 0){
                setExclusiveOwnerThread(null);
                flag = Boolean.TRUE;
            }
            //不存在线程问题 ，  重入性问题
            setState(status);
            return flag;
        }

        public Condition newConditionObject(){
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helpLock.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helpLock.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helpLock.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helpLock.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helpLock.release(1);
    }

    @Override
    public Condition newCondition() {
        return helpLock.newConditionObject();
    }
}


class Demo{



    private MyLock lock = new MyLock();

    private ReentrantLock reentrantLock = new ReentrantLock();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int m = 0 ;
    public void a(){
        readWriteLock.readLock().lock();
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public int next(){
        lock.lock();
        try {
            //TimeUnit.SECONDS.sleep(3);
            return m ++ ;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread[] th = new Thread[20];
        Demo demo = new Demo();
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() ->{
                demo.a();
            });
            th[i].start();
        }
    }


}
