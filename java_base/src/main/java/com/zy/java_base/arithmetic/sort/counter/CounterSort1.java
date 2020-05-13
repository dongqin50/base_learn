package com.zy.java_base.arithmetic.sort.counter;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 计数排序
 * 2020/5/7
 * 用空间换时间
 * newArray的最大长度是MaxValue - MinValue + 1;
 */
public class CounterSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        int minValue = arrays[0];
        int maxValue = arrays[0];

        for(int i = 1; i < arrays.length; i++){
            if(arrays[i] < minValue) minValue = arrays[i];
            if(arrays[i] > maxValue) maxValue = arrays[i];
        }

        int[] newArray = new int[maxValue-minValue+1];

        int defaultInterval = 0;

        if(minValue < 0 ){
            defaultInterval = -minValue;
        }
        for(int i = 0; i < newArray.length;i++){
            newArray[i] = 0;
        }

        for(int i = 0 ; i < arrays.length; i++){
            newArray[arrays[i] + defaultInterval]++;
        }

        for(int i = 0,j=0; i < newArray.length; i++){
            int count = newArray[i];
            while (count-->0){
                arrays[j++] = i;
            }
        }
        return arrays;
    }
}
