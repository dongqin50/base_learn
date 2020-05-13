package com.zy.java_base.arithmetic.sort.select;


import com.zy.java_base.arithmetic.CommonUtils;
import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

public class SelectSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] array) {
        array = Arrays.copyOf(array,array.length);

        for(int i = 0 ; i < array.length-1; i++){
            int minIndex = i;
            for(int j = i+1;j<array.length;j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            if(i != minIndex){
                CommonUtils.swap(array,i,minIndex);
            }
        }
        return array;
    }
}
