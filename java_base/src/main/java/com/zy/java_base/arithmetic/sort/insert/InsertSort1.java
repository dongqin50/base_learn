package com.zy.java_base.arithmetic.sort.insert;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

/**
 * 采用i，j标记法来做
 * 花费时间： 20分钟
 */
public class InsertSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {
        int[] array = Arrays.copyOf(sortArray,sortArray.length);
        int i = 0, j=1;

        while (i<j && j < array.length){
            int num = array[j];
            int jj = j;
            int ii = i;
            //查找位置
            while (i>0&&array[i]>num){
                i--;
            }
            i++;
            //移动
            while (i<j){
               array[j--]=array[ii--];
            }
            array[i] = num;
            i=jj;
            j=jj+1;
        }


        return array;
    }




}
