package com.atguigu.sh.juc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class  MyCache{

    public volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        readWriteLock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"写入数据");
            try{ TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t"+"写入完成");
        }catch (Exception e){
             e.printStackTrace();   
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){


        readWriteLock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"获取数据");
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t"+"获取完成");
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}


/**
 *
 * 读写锁
 *
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取资源都应该可以同时进行，
 * 但是
 * 如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或写
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {



        MyCache myCache = new MyCache();

        for (int i = 1; i<=5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i<=5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
