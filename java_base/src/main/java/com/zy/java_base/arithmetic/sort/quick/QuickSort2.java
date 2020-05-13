package com.zy.java_base.arithmetic.sort.quick;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 100000  ------ 平均速度在12-20之间
 *
 * 2020/4/26  ------ 花费1小时
 * 注意点：l >= h , 别只是l == h来判断，会失误
 */
public class QuickSort2 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        sortArray = Arrays.copyOf(sortArray,sortArray.length);
        sort(sortArray,0,sortArray.length-1);
        return sortArray;
    }


    public void sort(int[] array,int l ,int h){

        if(l >= h) return;

        int index = sortArray(array,l , h);

        sort(array,l,index-1);
        sort(array,index+1,h);
    }

    public int sortArray(int[] array , int l ,int h){

        int num = array[l];
        boolean direct = true;

        while (l < h) {

            if(direct){

                if(array[h] < num){
                    array[l++] = array[h];
                    direct = false;
                }else {
                    h--;
                }

            }else {
                if(array[l] > num){
                    array[h--] = array[l];
                    direct = true;
                }else {
                    l++;
                }
            }

        }
        array[l] = num;

        return l;
    }

}
