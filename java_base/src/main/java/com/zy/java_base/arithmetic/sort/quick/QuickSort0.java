package com.zy.java_base.arithmetic.sort.quick;


import com.zy.java_base.arithmetic.factory.ISortFactory;

public class QuickSort0 implements ISortFactory {

    @Override
    public int[] sort(int[] sortArray) {

        quickSort(sortArray,0,sortArray.length-1);

        return sortArray;
    }

    //快速排序     31  21  59  68  12  40
    //    x=31
    public static void quickSort(int[] array,int begin,int end){
        if(end-begin<=0) return;
        int x=array[begin];
        int low=begin;//0
        int high=end;//5
        //由于会从两头取数据，需要一个方向
        boolean direction=true;
        L1:
        while(low<high){
            if(direction){//从右往左找
                for(int i=high;i>low;i--){
                    if(array[i]<=x){
                        array[low++]=array[i];
                        high=i;
                        direction=!direction;
                        continue L1;
                    }
                }
                high=low;//如果上面的if从未进入，让两个指针重合
            }else{
                for(int i=low;i<high;i++){
                    if(array[i]>=x){
                        array[high--]=array[i];
                        low=i;
                        direction=!direction;
                        continue L1;
                    }
                }
                low=high;
            }
        }
        //把最后找到的值 放入中间位置
        array[low]=x;
        //开始完成左右两边的操作
        quickSort(array,begin,low-1);
        quickSort(array,low+1,end);
    }
}
