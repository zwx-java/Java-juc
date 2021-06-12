package com.atguigu.sh.juc;

interface Foo
{
   // public void sayHello();

    public int add(int x,int y);

    default int div(int x,int y){
        System.out.println("你最");
        return x/y;
    }

    public static int sub(int x,int y){
        return x-y;
    }
}

/**
 * 2.lambda Express
 * 2.1  口诀
 *          拷贝小括号，写死右箭头，落地大括号
 *
 * 2.2 @FunctionalInterFace  函数式接口默认有这个注解
 *
 * 2.3 @default
 *
 * 2.4 静态方法实现
 */
public class LambdaExpressDemo {

    public static void main(String[] args) {

//        Foo foo = () -> { System.out.println("你最帅");};
//        foo.sayHello();

        Foo foo = (x,y) -> {
            System.out.println("x加y是多少");
            return x+y;
        };
        int result = foo.add(3,5);
        System.out.println(result);
        System.out.println(foo.div(10,2));
        System.out.println(Foo.sub(10,2));
    }
}
