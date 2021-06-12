package com.atguigu.sh.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class  MyThread implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception {

        
        System.out.println("*****come in here");
        try{ TimeUnit.MILLISECONDS.sleep(4000);} catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;

    }
}

/**
 * 多线程中，（继承Thread类，实现Runnable接口）
 * 第三种获得多线程的方式
 */
public class CallableDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask,"A").start();

        System.out.println(futureTask.get());
    }
}
