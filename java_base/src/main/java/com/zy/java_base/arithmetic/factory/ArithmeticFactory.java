package com.zy.java_base.arithmetic.factory;

public class ArithmeticFactory {

    public static  <T extends IFactory> T createClass(Class<T> clazz){

        try {
            IFactory sortFactory = (IFactory) Class.forName(clazz.getName()).newInstance();
            return (T) sortFactory;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
