package com.zy.java_base.arithmetic.sort.insert;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/5/7 花费时间：2小时
 * 问题：
 *  遇到这个问题：
 *    array[l+1] = array[l]
 *
 */
public class InsertSort2 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        int l=0,h = 1,p = 0;

        while (h < arrays.length){
            int num = arrays[h];
            while (p >= 0){
                if (num > arrays[p]){
                    break;
                }
                p--;
            }
            while (p<l){
                arrays[l+1] = arrays[l];
                l--;
            }
            arrays[++p] = num;
            h++;
            l = h-1;
            p = l;
        }
        return arrays;
    }
}
