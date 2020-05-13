package com.zy.java_base.arithmetic.sort.merger;

import com.zy.java_base.arithmetic.factory.ISortFactory
import java.util.*

//归并排序
/**
 * 测试失败
 */
class MergerSort : ISortFactory {
    val DEFAULT_VALUE = -1111111111
    val RIGHT = "right"
    val LEFT = "left"


    override fun sort(sortArray: IntArray?): IntArray {
        return this.sort(Arrays.copyOf(sortArray,sortArray!!.size/2),Arrays.copyOf(sortArray,-sortArray!!.size/2))

    }

    fun sort(leftArray:IntArray?,rightArray:IntArray?):IntArray{

        val arrays = IntArray(leftArray!!.size+rightArray!!.size)

        var l = 0;
        var r = 0;
        var curent = LEFT;


        while(l < leftArray.size || r < rightArray.size){

            var left = DEFAULT_VALUE
            var right = DEFAULT_VALUE

            if(l < leftArray.size){
                left = leftArray[l]
            }

            if(r < rightArray.size){
                right = rightArray[r]
            }

            if(left == DEFAULT_VALUE){
                arrays[l+r+1] = left
            }else if(right == DEFAULT_VALUE){
                arrays[l+r+1] = right
            }else{
                if(left <= right){
                    arrays[l+r+1] = left
                    l++
                }else{
                    arrays[l+r+1] = right
                }
            }

            if( LEFT == curent){

            }

        }


        return arrays;
    }
}