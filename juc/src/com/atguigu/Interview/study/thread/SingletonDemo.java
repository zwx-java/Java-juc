package com.atguigu.Interview.study.thread;

public class SingletonDemo
{
    private static volatile SingletonDemo instance = null;

    private SingletonDemo()
    {
        System.out.println(Thread.currentThread().getName()+"\t我是构造方法");
    }

    //DCL (doouble Check Lock 双端检索机制)
    public static SingletonDemo getInstance()
    {
        if (instance == null)
        {
            synchronized (SingletonDemo.class)
            {
                if (instance == null)
                {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
