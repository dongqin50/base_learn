package com.zy.java_base.arithmetic.sort.merger;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 2020/4/30
 * 不细心：
 * 传值有问题
 */
public class MergerSort2 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        int[] array = Arrays.copyOf(sortArray,sortArray.length);
        sort(array,0,array.length-1);
        return array;
    }

    private void sort(int[] array,int l,int h){

        if(l>=h)return;

        int mid = (l+h)/2;
        sort(array,l,mid);
        sort(array,mid+1,h);
        sortArray(array,l,mid,h);
    }

    private void sortArray(int[] array,int l,int m,int h){

        //-------2020/5/7之前的-------
//        int[] leftArray = new int[m-l];
//        int[] rightArray = new int[h-m+1];

        //-------2020/5/7修改的地方-------
       int[] leftArray = new int[m-l+1];
        int[] rightArray = new int[h-m];

        for(int i = 0;i<leftArray.length;i++){
            leftArray[i]=array[i+l];
        }
        for(int i = 0;i < rightArray.length;i++){
            //-------2020/5/7之前的-------
//            rightArray[i] = array[i+m];
            //-------2020/5/7修改的地方-------
            rightArray[i] = array[i+m+1];
        }

        int i=0,j=0,n=l;
        while (i<leftArray.length&&j<rightArray.length){
            if(leftArray[i]<=rightArray[j]){
                array[n++]=leftArray[i++];
            }else {
                array[n++]=rightArray[j++];
            }
        }

        while (i<leftArray.length){
            array[n++]=leftArray[i++];
        }
        while (j<leftArray.length){
            array[n++]=rightArray[j++];
        }

    }
}
