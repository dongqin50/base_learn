package com.zy.java_base.arithmetic.select.binary;

import com.zy.java_base.arithmetic.factory.ArithmeticFactory;
import com.zy.java_base.arithmetic.factory.ISelectFactory;
import com.zy.java_base.arithmetic.sort.counter.CounterSort1;

public class BinarySelect1 implements ISelectFactory {

    @Override
    public boolean select(int[] arrays, int num) {
        arrays = ArithmeticFactory.createClass(CounterSort1.class).sort(arrays);
        return binary(arrays,0,arrays.length-1,num);
    }

    private boolean binary(int[] arrays,int l,int h,int num){

        if(l>=h) return false;
        int mid = (h+l)/2;
        if(arrays[mid] == num) return true;
        if(num < arrays[mid]){
            return binary(arrays,l,mid-1,num);
        }else {
           return binary(arrays,mid+1,h,num);
        }
    }
}
