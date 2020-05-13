package com.zy.java_base.arithmetic.sort.insert;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

//插入排序
public class InsertSort implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {

        int[] arr = Arrays.copyOf(sortArray,sortArray.length);
        for(int i =  1;i<arr.length;i++){
            int currentNum = arr[i];
            int j = i-1;
            for(;j >= 0&&currentNum<arr[j];j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = currentNum;
        }
        return arr;
    }
}
