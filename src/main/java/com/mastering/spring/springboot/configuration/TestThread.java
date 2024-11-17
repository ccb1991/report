package com.mastering.spring.springboot.configuration;

import io.swagger.models.auth.In;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TestThread {
    private static ThreadPoolExecutor threadPoolExecutor;
//    private List<Integer> list;
//    private LinkedBlockingQueue<Integer> list;
    private ConcurrentHashMap<String,Integer> list;
//    private LinkedList<Integer> list;

    public TestThread(List<Integer> list){
        threadPoolExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(4);
//        this.list= Collections.synchronizedList(list);
//        this.list= list;
//        this.list=new LinkedList<>(list);
        this.list=new ConcurrentHashMap();
        this.list.put("test",list.get(0));
    }

    public void execute() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(this.list.size());
        ReentrantLock reentrantLock=new ReentrantLock();
       for(int i=0;i<10;i++){
            threadPoolExecutor.execute(new TestThreadRunable(list,countDownLatch,reentrantLock));
            countDownLatch.await(10, TimeUnit.MICROSECONDS);
        }
//            threadPoolExecutor.execute(new TestThreadRunable(value,countDownLatch));
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13);
        TestThread testThread=new TestThread(list);
        testThread.execute();
        Integer sum=list.stream().reduce(0,(x,y)->x+y);
        List<Integer> map=list.parallelStream().map(x->x+1).collect(Collectors.toList());
        File inputFile=new File("H:\\foxmock\\foxmock\\data\\" +
                "com.mastering.spring.springboot.service.ExamServiceImpl#queryQuestionByAge");
        Path path= Paths.get("H:\\foxmock\\foxmock\\data\\com" +
                ".mastering.spring.springboot.service.ExamServiceImpl#queryQuestionByAge");
        List<String> lines= Files.readAllLines(path, StandardCharsets.UTF_8);
        File file=path.toFile();
        Path p=file.toPath();
        System.out.println(" ");
    }
}
