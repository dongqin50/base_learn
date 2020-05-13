package com.zy.java_base.utils;

public class RandomUtils {


    public static int[] createRandomArray(int arrayLength){
        int[] arrays = new int[arrayLength];

        for(int i = 0; i < arrayLength;i++){
            arrays[i] = (int) (Math.random() * 1000000);
        }

        return arrays;
    }
}
