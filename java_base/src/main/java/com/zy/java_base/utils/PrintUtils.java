package com.zy.java_base.utils;

public class PrintUtils {

    public static String  println(int[] arrays){
        StringBuffer sb = new StringBuffer("数组： ");
        if(arrays != null && arrays.length > 0){
            for(int a : arrays){
                sb.append(a+",");
            }

        }

//        System.out.println(sb.toString());

        return sb.toString();
    }

    public static void println(String str){
        System.err.println(str);
    }


}
