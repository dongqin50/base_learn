package com.zy.java_base.designPattern.thread;

import java.util.Scanner;

public class ThreadDemo {


    static void PrintMessage(String message){
        String name = Thread.currentThread().getName();

        System.out.printf("currentThread   %s : %s ",name,message + " \n\n");
    }

    static class MessageThread implements Runnable{

        private long time;

        public MessageThread(long time) {
            this.time = time;
        }

        @Override
        public void run() {

            String[] messages = new String[]{"AAA","BBB","ccddfdfd","iiiiiiiii"};

            for(String m : messages){

                try {
                    Thread.sleep(time*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                PrintMessage(m + "  time " + time);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        PrintMessage("start------------");

        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        long activityTime = Long.valueOf(str) * 1000;

        Thread t = new Thread(new MessageThread((long) (Math.random()*10)));
        Thread t2 = new Thread(new MessageThread((long) (Math.random()*10)));
        Thread t3 = new Thread(new MessageThread((long) (Math.random()*10)));
        Thread t4 = new Thread(new MessageThread((long) (Math.random()*10)));
        Thread t5 = new Thread(new MessageThread((long) (Math.random()*10)));
        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        PrintMessage("MessageThread waiting end------------");

        while (t.isAlive()){

            t.join(100000);
            t2.join(100000);
            long time = System.currentTimeMillis() - startTime;
            if(( time > activityTime) && t.isAlive()){
                PrintMessage("MessageThread interrupt------------ before " + time);
                t.interrupt();

                PrintMessage("MessageThread interrupt------------ after " + (System.currentTimeMillis()-startTime)  + " isAlive : " + t.isAlive());
//                t.join();
                PrintMessage("MessageThread join------------" + (System.currentTimeMillis()-startTime) + " isAlive : " + t.isAlive() );
            }
            PrintMessage("Finished------------" + (System.currentTimeMillis()-startTime));
        }

    }

}
