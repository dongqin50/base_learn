package com.zy.java_base.arithmetic.sort.quick;


import com.zy.java_base.arithmetic.factory.ISortFactory;

/**
 * 快速排序  ---- 2020/4/25
 *
 *  花费时间 ：
 *      1.手写花费半小时，写的不是很完整
 *      2.录入电脑，调试花费一天
 *
 */
public class QuickSort1 implements ISortFactory {


    @Override
    public int[] sort(int[] array) {

        for(int n : array){
            System.out.print(n + " ");
        }

        System.out.println(" ");

        sort(array,0,array.length-1);

        for(int n : array){
            System.out.print(n + " ");
        }

        return array;
    }


    private void sort(int[] array,int low,int high){


        if(low >= high) return;

        int index = sortArray(array,low,high);
        sort(array,low,index-1);
        sort(array,index+1,high);
    }


    public int sortArray(int[] array,int l,int h){


        if(l >= h) return l;

        int num = array[l];

        boolean flag = true;

        while (l < h){
            if(flag){
                if(array[h] <= num){
                    array[l] = array[h];
                    l++;
                    flag = false;
                }else {
                    h--;
                }
            }else {
                if(array[l] > num){
                    array[h] = array[l];
                    flag = true;
                    h--;
                }else {
                    l++;
                }
            }
        }

        array[l] = num;

        return l;
    }
}
