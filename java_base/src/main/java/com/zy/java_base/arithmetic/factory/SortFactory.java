package com.zy.java_base.arithmetic.factory;

import java.util.Arrays;

public class SortFactory implements ISortFactory{

    @Override
    public int[] sort(int[] sortArray) {
        sortArray = Arrays.copyOf(sortArray,sortArray.length);
        return sortArray;
    }



}
