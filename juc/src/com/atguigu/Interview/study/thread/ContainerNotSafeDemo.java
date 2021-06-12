package com.atguigu.Interview.study.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo
{
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    /**
     * 1.故障现象
     *          java.util.ConcurrentModificationException
     * 2.导致原因
     * 3.解决方案
     * 3.1 new Vector<>;不建议用
     * 3.2 Collections.synchronizedList  Collections工具类解决
     * 3.3
     * 4.优化建议
     */
}
