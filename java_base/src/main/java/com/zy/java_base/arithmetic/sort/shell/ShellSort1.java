package com.zy.java_base.arithmetic.sort.shell;

import com.zy.java_base.arithmetic.factory.ArithmeticFactory;
import com.zy.java_base.arithmetic.factory.ISortFactory;
import com.zy.java_base.arithmetic.sort.insert.InsertSort1;

import java.util.Arrays;


/**
 * 2020/4/30
 *
 *
 */
public class ShellSort1 implements ISortFactory {

    @Override
    public int[] sort(int[] array) {
        array = Arrays.copyOf(array,array.length);

        double a = Math.log10(array.length);
        int interval = Math.round((float)a);
        System.out.println(" size : "+array.length+"  interval " + interval + "  a = " + a);
        sort(array,interval);
        return array;
    }

    private void swap(int[] array,int l ,int r){
        int num = array[l];
        array[l] = array[r];
        array[r]=num;
    }


    private void sort(int[] array,int interval){

//        while (interval>0){
            for(int i = 0 ; i < array.length/interval;i++){
                for(int l = i,r = interval+i; r < array.length;l+=interval+i,r+=interval+i){
                    if(array[l]>array[r]){
                        swap(array,l,r);
                    }
                }
            }
            interval = interval/10;
//        }

        if(interval == 1){
            ArithmeticFactory.createClass(InsertSort1.class).sort(array);
        }else if(interval<=0){
            return;
        }else {
            sort(array,interval);
        }
    }


}
