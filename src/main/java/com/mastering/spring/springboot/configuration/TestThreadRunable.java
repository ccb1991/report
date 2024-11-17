package com.mastering.spring.springboot.configuration;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadRunable implements Runnable {
    private CountDownLatch countDownLatch;
    private ConcurrentHashMap<String,Integer> value;

    private ReentrantLock reentrantLock;
//    private LinkedList<Integer>  value;
    @SneakyThrows
    @Override
    public void run() {
        try {
//            if (value==10){
//                throw new InterruptedException();
//            }
//            Integer v=value.get(0)+1;
//            value.set(0,v);
            reentrantLock.lock();
            Integer v=value.get("test")+1;
            value.put("test",v);
            reentrantLock.unlock();
            System.out.println(value.get("test"));
//        } catch (Exception e){
//            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

//    public TestThreadRunable(List<Integer> value, CountDownLatch countDownLatch){
//    public TestThreadRunable(LinkedBlockingQueue value, CountDownLatch countDownLatch){
    public TestThreadRunable(ConcurrentHashMap value, CountDownLatch countDownLatch,
                             ReentrantLock reentrantLock){
//    public TestThreadRunable(LinkedList value, CountDownLatch countDownLatch){
        this.value=value;
        this.reentrantLock=reentrantLock;
        this.countDownLatch=countDownLatch;
    }
}
