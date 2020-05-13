package com.zy.java_base.jvm;


/**
 *
 * 针对HotSpot VM ，并不区分虚拟机栈和本地方法栈
 *
 * 关于虚拟机栈和本地方法栈，在Java中虚拟机规范中描述来两种异常：
 *
 *   1. 线程请求的栈的深度 > 虚拟机最大栈深度 -> StackOverflowError
 *
 *   2. 虚拟机扩展栈时，无法申请足够的内存空间 -> OutOfMemoryError
 *
 *
 * 在单线程中：
 *
 * VM Option : -Xss168k -> 使用-Xss来减少栈内存容量，结果抛出StackOverflowError异常
 *
 * 定义来大量的本地变量，增大此方法帧中本地变量表的长度，结果抛出StackOverflowError异常
 *
 * 结论：在单线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出的都是StackOverflowError异常
 *
 */

public class JavaVMStackSOF {

    public   int statckLenght = 1;

    public void stackLeak(){
        statckLenght++;
        stackLeak();
    }

    private void dontStop(){
        while (true){}
    }

    public void stackLeakByThread(){

        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });

            thread.start();
        }

    }

    public static void main(String[] args) throws Throwable{

        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();

        try {
            //单线程
//            javaVMStackSOF.stackLeak();

            //多线程
            javaVMStackSOF.stackLeakByThread();
        }catch (Throwable e){
//            System.out.println("stack length : " + javaVMStackSOF.statckLenght);
            throw e;
        }

    }
}
