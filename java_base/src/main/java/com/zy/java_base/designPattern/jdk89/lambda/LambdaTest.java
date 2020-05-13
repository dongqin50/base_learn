package com.zy.java_base.designPattern.jdk89.lambda;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LambdaTest {

    public static void main(String[] args){

//        YFunction<Integer,Integer> name = integer -> integer * 2;
//        YFunction<Integer,Integer> square = integer -> integer * integer;
//
//        int andThen = name.andThen(square).apply(3);
//        int compose = name.compose(square).apply(3);
//        System.out.printf("andThen  : %d  \ncompose : %d" ,andThen,compose);



        Map<Integer,StringBuilder> map=new HashMap<Integer,StringBuilder>();
        map.put(1,new StringBuilder("c1"));
        map.put(2,new StringBuilder("c2"));
        map.put(3,new StringBuilder("c3"));

        Map<Integer,StringBuilder> unmodifiableMap= Collections.unmodifiableMap(map);
        //这时候如果再往unmodifiableMap中添加元素，会发生错误
        //unmodifiableMap.put(4,new StringBuilder("c4"));
        System.out.println(" unmodifiableMap  before : " + unmodifiableMap.get(3));
        System.out.println(" map  before : " + map.get(3));
        unmodifiableMap.get(3).append("new");
        System.out.println(" map  after : " + map.get(3));
        System.out.println(" unmodifiableMap  after : " + unmodifiableMap.get(3));

    }
}
