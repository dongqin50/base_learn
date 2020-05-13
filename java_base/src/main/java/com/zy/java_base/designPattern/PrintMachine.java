package com.zy.java_base.designPattern;

public class PrintMachine implements Print {


    @Override
    public void print(int aaa, int bb) {
        System.out.println("---------- PrintMachine print -------------");
    }

    @Override
    public void print22(String aaa) {
        System.out.println("---------- PrintMachine print22 -------------");
    }
}
