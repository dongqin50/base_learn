package com.zy.java_base.reflect;

public class ReflectTest {

    public static void main(String[] args){




    }


    public <T extends Object> T createTest(Class<T> clazz){

        Object object = null;

        try {
            object = Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (T) object;

    }
}
