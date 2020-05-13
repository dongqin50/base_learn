package com.zy.java_base.arithmetic.sort.merger;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 *
 * 归并 ------- 2020/4/26
 * 花费时间 ----- 2 小时 ---- 调试成功
 * 疑点：中间值总是无法确定
 *
 */
public class MergerSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        int[] array = Arrays.copyOf(sortArray,sortArray.length);
        sortMerger(array,0,array.length-1);
        return array;
    }

    private void sortMerger(int[] array,int l,int h){

        if(l >= h) return;

        int mid = (h + l )/2;
        sortMerger(array,l, mid);
        sortMerger(array,mid+1,h);
        sort(array,l,mid,h);
    }


    private void sort(int[] array,int l,int mid,int h){

        if(l >= h) return;

        //------ 2020/5/7之前的-------

//        int[] leftArray = new int[mid-l];
//        int[] rightArray = new int[h-mid+1];

        //-------2020/5/7修改的地方-------
        int[] leftArray = new int[mid-l+1];
        int[] rightArray = new int[h-mid];
        for(int i = 0 ; i < leftArray.length;i++){
            leftArray[i] = array[i+l];
        }

        for (int j = 0; j < rightArray.length; j++){
            //------ 2020/5/7之前的-------
//            rightArray[j] = array[j + mid];

            //-------2020/5/7修改的地方-------
            rightArray[j] = array[j + mid + 1];
        }

        int i = 0,j = 0,n = l;

        while (i < leftArray.length && j < rightArray.length){

            if(leftArray[i] <= rightArray[j]){
                array[n] = leftArray[i];
                i++;
            }else {
                array[n] = rightArray[j];
                j++;
            }
            n++;
        }
        while (i >= leftArray.length && j < rightArray.length){
            array[n] = rightArray[j];
            j++;
            n++;
        }
        while (j >= rightArray.length && i < leftArray.length){
            array[n] = leftArray[i];
            n++;
            i++;
        }
    }
}
