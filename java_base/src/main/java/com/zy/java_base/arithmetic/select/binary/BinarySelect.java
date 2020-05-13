package com.zy.java_base.arithmetic.select.binary;

import com.zy.java_base.arithmetic.factory.ArithmeticFactory;
import com.zy.java_base.arithmetic.factory.ISelectFactory;
import com.zy.java_base.arithmetic.sort.merger.MergerSort1;

import java.util.Arrays;

/**
 * 二分查找法
 *
 * 2020/4/26
 */
public class BinarySelect implements ISelectFactory {

    @Override
    public boolean select(int[] arrays, int num) {
        arrays = Arrays.copyOf(arrays,arrays.length);

        //先对数据进行排序
        sort(arrays);
        return select(arrays,0,arrays.length-1,num) != -1;
    }

    private void sort(int[] arrays){
        ArithmeticFactory.createClass(MergerSort1.class).sort(arrays);
    }

    public int select(int[] arrays,int l , int r,int num){


        int mid = (r+l)/2;

        if(arrays[mid] == num){
            return mid;
        }else if(l >= r) return -1;

        int lResult = select(arrays,l,mid,num);
        int rResult = select(arrays,mid+1,r,num);

        return lResult != -1?lResult:(rResult != -1?rResult:-1);

    }



}
