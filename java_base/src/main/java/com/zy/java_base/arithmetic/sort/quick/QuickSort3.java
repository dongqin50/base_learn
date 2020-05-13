package com.zy.java_base.arithmetic.sort.quick;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/4/30
 * 问题：
 * 不细心:
 * sort(int[],int,int)里面少了l>=h
 * sortArray 将h--写成了num--
 * 导致死循环
 */
public class QuickSort3 implements ISortFactory {

    @Override
    public int[] sort(int[] array) {
        array = Arrays.copyOf(array,array.length);
        sort(array,0,array.length-1);
        return array;
    }

    private void sort(int[] array,int l ,int h){

        if(l>=h)return;

       int mid = sortArray(array,l,h);
       sort(array,l,mid-1);
       sort(array,mid+1,h);
    }

    private int sortArray(int[] array,int l , int h){

        int num = array[l];
        boolean direct=true;
        while (l<h){
            if(direct){
                if(array[h]<=num){
                    array[l++]=array[h];
                    direct=false;
                }else {
                    h--;
                }
            }else {
                if(array[l]>num){
                    array[h--]=array[l];
                    direct=true;
                }else {
                    l++;
                }
            }
        }
        array[l]=num;
        return l;
    }


}
