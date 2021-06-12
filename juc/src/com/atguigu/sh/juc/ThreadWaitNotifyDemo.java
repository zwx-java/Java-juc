package com.atguigu.sh.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner //资源类
{
    private  int number =0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException{

        lock.lock();
        try
        {
            //1.判断
            while (number!=0){
                //A等待
               condition.await();
            }
            //2.干活
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            condition.signalAll();
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException{

        lock.lock();
        try
        {
            //1.判断
            while (number ==0){
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            condition.signalAll();
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}

/**
 * InterruptedException  :线程中断异常
 * 题目：现在两个线程，可以操作一个初始为零的变量
 *实现一个线程对该变量加一，一个线程对该变量减一。
 * 实现交替，来十轮，变量初始值为零
 *
 * 1.高内聚低耦合的前提下，线程操作资源类
 * 2.判断\干活\通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒,也即（判断只用while,不能用if）
 */


public class ThreadWaitNotifyDemo
{
    public static void main(String[] args) throws Exception {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } 
        },"A").start();


        new Thread(() -> {
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}
