package com.zy.java_base.arithmetic.sort.select;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;
//选择排序
public class SelectSort implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        sortArray = Arrays.copyOf(sortArray,sortArray.length);

        int[] array = Arrays.copyOf(sortArray,sortArray.length);

        for(int i = 0;i < array.length-1;i++){
            int min = i;
            for (int j = i+1; j < array.length;j++){
                if(array[j] < array[min]){
                    min = j;
                }
            }

            if(min != i){
                int c = array[min];
                array[min] = array[i];
                array[i]=c;
            }
        }





        return array;
    }
}
