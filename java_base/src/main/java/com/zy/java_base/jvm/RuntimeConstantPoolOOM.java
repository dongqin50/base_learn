package com.zy.java_base.jvm;


public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {

        String str1 = new StringBuilder("计算器").append("软件").toString();

        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("in").append("t").toString();
        String str3 = new StringBuilder("ja1").append("va1").toString();
        String str4 = new StringBuilder("ja11").append("va11").toString();


        System.out.println(str3.intern() == str3);
        System.out.println(str4.intern() == str4);
        System.out.println(str2.intern() == str2);

    }
}
