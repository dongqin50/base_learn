package com.zy.java_base.arithmetic.select;


import com.zy.java_base.arithmetic.factory.ISelectFactory;

import java.util.Arrays;

public class OrderSelect implements ISelectFactory {

    @Override
    public boolean select(int[] arrays, int num) {
        arrays = Arrays.copyOf(arrays,arrays.length);

        for(int i = 0 ; i < arrays.length; i++){
            if(arrays[i] == num) return true;
        }

        return false;
    }
}
