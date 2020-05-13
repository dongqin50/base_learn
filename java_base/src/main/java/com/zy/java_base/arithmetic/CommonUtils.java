package com.zy.java_base.arithmetic;

public class CommonUtils {

    public static void swap(int[] array,int l,int r){
        int num = array[l];
        array[l] = array[r];
        array[r] = num;
    }

}
