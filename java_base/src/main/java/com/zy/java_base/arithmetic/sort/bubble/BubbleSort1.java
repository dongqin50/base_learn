package com.zy.java_base.arithmetic.sort.bubble;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

public class BubbleSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {

        int[] array = Arrays.copyOf(sortArray,sortArray.length);

        int count=0;
        int swap = 0;
        for(int i = 0 ; i < array.length ; i++){
            for (int j = 0, jj = j+1; jj < array.length-i;jj++,j++){
                count++;
                if(array[j]>array[jj]){
                    int num = array[jj];
                    array[jj] = array[j];
                    array[j] = num;
                    swap++;
                }

            }
            for(int a : array){
                System.out.print(a +",");
            }
            System.out.println(" ");
        }
        for(int a : array){
            System.out.print(a +",");
        }
        System.out.println(" ");
        System.out.println(" count : " + count + " swap : " + swap);
        return array;
    }
}
