package com.zy.java_base.arithmetic.sort.bubble;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/5/7
 *问题：
 *  left和right范围有问题
 */
public class BubbleSort2 implements ISortFactory {


    @Override
    public int[] sort(int[] sortArray) {
        int[] array = Arrays.copyOf(sortArray,sortArray.length);

        for(int i = 0;i<array.length;i++){
            for(int right = array.length-1,left = right-1; left >= i; left--,right--){
                if(array[left]>array[right]){
                    int num = array[left];
                    array[left] = array[right];
                    array[right] = num;
                }
            }
        }

        return array;
    }
}
