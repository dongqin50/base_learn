package com.zy.java_base.annotation;

import java.lang.reflect.Field;

public class TestAnnotationAnalysis {

    public void analysisTestAnnotation(){
        try {
            // 获取要解析的类
            Class cls = Class.forName("com.zy.testdemo.annotation.TestAnnotation");
            // 拿到所有Field
            Field[] declaredFields = cls.getDeclaredFields();
            for(Field field : declaredFields){
                // 获取Field上的注解
                TestAnnotation annotation = field.getAnnotation(TestAnnotation.class);
                if(annotation != null){
                    // 获取注解值
                    String value = annotation.value();
                    System.out.println(value);
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
