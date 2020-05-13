package com.zy.java_base.arithmetic.sort.counter;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

public class CounterSort implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        int[] array = Arrays.copyOf(sortArray,sortArray.length);
        int min = array[0];
        int max = array[0];

        for(int i = 1; i < array.length;i++){

            if(array[i]>max){
                max = array[i];
            }
            if(array[i]<min){
                min = array[i];
            }
        }

        int size = max + 1;
        int[] dataArray = new int[size];
//        System.out.println(" min = " + min + " max = " + max + " size = " + size);

        for(int i = 0; i < size;i++){
            dataArray[i] = 0;
        }
        for(int i = 0; i < array.length;i++){
            int index = array[i];
//            System.out.println(" index = " + index);
            dataArray[index] = dataArray[index] + 1;
        }
        int n = 0;
        for(int i = 0; i < size;i++){
            for(int j = 0; j < dataArray[i]; j++){
                array[n++] = dataArray[j];
            }
        }
        return array;
    }

}
