package com.zy.java_base.arithmetic.sort.merger;

import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 *  2020/5/7
 * 问题：
 *  一直无法成功，解决办法：
 *      int[] leftArray = new int[m-l+1]
 *      int[] rightArray = new int[h-m]
 *  之前有问题的地方-------之前写的都是这样写的，所以全部有问题
 *       int[] leftArray = new int[m-l]
 *       int[] rightArray = new int[h-m+1]
 */

public class MergerSort3 implements ISortFactory {

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        sort(arrays,0,arrays.length-1);

        return arrays;
    }

    private void sort(int[] arrays,int l,int h){

        if(l>=h)return;

        int m = (l + h) / 2;

        sort(arrays,l,m);
        sort(arrays,m+1,h);
        merger(arrays,l,m,h);
    }


    private void merger(int[] arrays,int l,int m,int h){

        if(l>=h) return;

        int[] leftArray = new int[m-l+1];
        int[] rightArray = new int[h-m];


        for(int i = 0; i < leftArray.length;i++){
            leftArray[i] = arrays[i+l];
        }

        for (int i = 0;i < rightArray.length;i++){
            rightArray[i] = arrays[i+m+1];
        }

        int ll = 0,rr = 0,p = l;
        while (ll < leftArray.length&&rr < rightArray.length){
            if(leftArray[ll] <= rightArray[rr]){
                arrays[p++] = leftArray[ll++];
            }else {
                arrays[p++] = rightArray[rr++];
            }
        }
        while (ll<leftArray.length){
            arrays[p++] = leftArray[ll++];
        }
        while (rr<rightArray.length){
            arrays[p++] = rightArray[rr++];
        }

    }
}
