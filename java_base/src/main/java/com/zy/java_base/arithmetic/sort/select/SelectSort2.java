package com.zy.java_base.arithmetic.sort.select;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/5/7 花费时间：1小时
 * 问题：p++要慎用
 */
public class SelectSort2 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        int position=0;

        while (position < arrays.length){

            int minPosition = position;
            int p = minPosition + 1;

            while (p < arrays.length){
                if(arrays[p] < arrays[minPosition]){
                    minPosition = p;
                }
                p++;
            }

            int num = arrays[minPosition];
            arrays[minPosition] = arrays[position];
            arrays[position] = num;
            position++;
        }
        return arrays;
    }
}
