package com.atguigu.sh.juc;


import java.util.concurrent.TimeUnit;

class Phone{

    public static synchronized void sendEmail()throws Exception{

        //暂停一会线程
        try{ TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("----sendEmail");
    }

    public static synchronized void sendSMS()throws Exception{
        System.out.println("----SMS");
    }

    public void sayHello(){

        System.out.println("-----hello");
    }

}

/**
 * 题目：多线程8锁
 * 1 标准访问，先打印短信还是邮件
 * 2 停4秒在短信方法内，先打印短信还是邮件
 * 3 普通的hello方法，是先打短信还是hello
 * 4 现在有两部手机，先打印短信还是邮件
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件
 * 6 两个静态同步方法，2部手机，先打印短信还是邮件
 * 7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * 8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 */
public class Lock8 {

    public static void main(String[] args) {

        Phone phone = new Phone();
        Phone phone2 =  new Phone();

       new Thread(new Runnable()
       {
           @Override
           public void run()
           {
               try {
                   phone.sendEmail();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       },  "input thread name").start();



       new Thread(new Runnable()
       {
           @Override
           public void run()
           {
               try {
                        phone.sendSMS();
                   //phone.sayHello();
                   //phone2.sendSMS();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       },  "input thread name").start();
    }
}
