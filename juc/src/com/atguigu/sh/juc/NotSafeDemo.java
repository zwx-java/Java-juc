package com.atguigu.sh.juc;


import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * 题目：请举例说明集合类是不安全的
 *1.故障现象
 * java.util.ConcurrentModificationException
 *
 * 2.导致原因
 *
 * 3.解决方案
 *  3.1 方案一（不准用）：Vector 他是线程安全的 add里面加了锁
 *  3.2 方案二： Collections.synchronizedList(new ArrayList<>());
 *  3.3 方案三:    CopyOnWriteArrayList<>()
 *
 * 4.优化建议（同样的错误，不出现第二次）
 *
 */
public class NotSafeDemo {

    public static void main(String[] args) {

    }


    public static void mapNotSafe(){

        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                    map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(list);
                }
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
            }, "B").start();
        }

    }
}
