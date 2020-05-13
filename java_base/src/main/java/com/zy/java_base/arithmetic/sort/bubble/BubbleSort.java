package com.zy.java_base.arithmetic.sort.bubble;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

//冒泡算法
public class BubbleSort implements ISortFactory {

    @Override
    public  int[] sort(int[] sortArray){

        int[] arr = Arrays.copyOf(sortArray,sortArray.length);

        for(int i = arr.length-1;i > 1; i--){

            boolean isFinish = true;
            for (int j = 0; j < i;j++){
                if(arr[j] > arr[j+1]){
                    int c = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = c;
                    isFinish = false;
                }
            }

            if(isFinish){
                break;
            }
        }
        return arr;
    }

}
