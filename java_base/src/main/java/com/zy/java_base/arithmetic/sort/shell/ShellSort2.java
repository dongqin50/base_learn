package com.zy.java_base.arithmetic.sort.shell;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/5/7 花费时间：2小时
 *
 */
public class ShellSort2 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        int pow = (int) Math.log10(arrays.length);
        int interval = (int) Math.pow(10,pow);
        sort(arrays,interval);
        return arrays;
    }

    private void insertSort(int[] arrays){

        int l=0,r=1,p=l;

        while (r < arrays.length){
            int num = arrays[r];
            //查找位置
            while (p>=0){
               if(num > arrays[p]){
                   break;
               }
               p--;
           }
            //移动
            while (p < l){
                arrays[l+1] = arrays[l];
                l--;
            }
            //插入
            arrays[++p] = num;
           l = r;
           r = l+1;
           p = l;
        }
    }

    private void sort(int[] arrays,int interval){

        System.out.println(" interval : " + interval);
        if(interval <= 1){
            insertSort(arrays);
            return;
        }
        int count = arrays.length/interval;
        for(int i = 0 ; i < count;i++){
            for (int left = i,right = left+interval; right < arrays.length; left=left+interval,right=right+interval){
                if(arrays[left] > arrays[right]){
                    int num = arrays[left];
                    arrays[left] = arrays[right];
                    arrays[right] = num;
                }
            }
        }
        sort(arrays,interval>>1);
    }




}
