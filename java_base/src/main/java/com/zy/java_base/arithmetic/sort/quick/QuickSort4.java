package com.zy.java_base.arithmetic.sort.quick;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/5/7  完成时间：40分钟
 *
 */
public class QuickSort4 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);
        sort(arrays,0,arrays.length-1);
        return arrays;
    }

    private void sort(int[] arrays,int l ,int h){

        if(l >= h) return;

        int position = quickSort(arrays,l,h);

        if(position != -1){
            sort(arrays,l,position-1);
            sort(arrays,position + 1,h);
        }
    }

    private int quickSort(int[] arrays,int l , int h){

        if(l>=h) return -1;
        int num = arrays[l];
        boolean direct = true;
        while (l<h){
            if(direct){
                if(arrays[h]<num){
                    arrays[l++] = arrays[h];
                    direct = false;
                }else {
                    h--;
                }
            }else {
                if(arrays[l] > num){
                    arrays[h--] = arrays[l];
                    direct = true;
                }else {
                    l++;
                }
            }
        }
        arrays[l] = num;
        return l;
    }
}
