package com.zy.java_base.arithmetic.sort.shell;


import com.zy.java_base.arithmetic.factory.ISortFactory;

import java.util.Arrays;

//希尔排序
public class HillSortSort implements ISortFactory {


    @Override
    public int[] sort(int[] sortArray) {

        int[] arrays = Arrays.copyOf(sortArray,sortArray.length);

        return sort(arrays,arrays.length/2);
    }

    /**
     *
     * @param sortArray
     * @param added 增量
     * @return
     */
    private int[] sort(int[] sortArray,int added){

        if(sortArray == null || sortArray.length < 2||added < 1 ){
            return sortArray;
        }

        for(int s = 0; s < added;s++){
            for(int i = s+added;i < sortArray.length;i=i+added){
                int j = i - added;
                int currentNum = sortArray[i];
                for(;j >= s&&currentNum < sortArray[j];j=j-added){
                    sortArray[j+added] = sortArray[j];
                }
                sortArray[j+added] = currentNum;
            }
        }

        return added == 1?sortArray:sort(sortArray,added/2);

    }

}
