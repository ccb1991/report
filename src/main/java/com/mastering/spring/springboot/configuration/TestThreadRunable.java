package com.mastering.spring.springboot.configuration;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestThreadRunable implements Runnable {
    private CountDownLatch countDownLatch;
    private List<Integer>  value;
    @SneakyThrows
    @Override
    public void run() {
        try {
//            if (value==10){
//                throw new InterruptedException();
//            }
            Integer v=value.get(0)+1;
            value.set(0,v);
            System.out.println(value);
//        } catch (Exception e){
//            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    public TestThreadRunable(List<Integer> value, CountDownLatch countDownLatch){
        this.value=value;
        this.countDownLatch=countDownLatch;
    }
}
