package com.zy.java_base.designPattern.singleton;

public class App {

    private static SingletonExample<Singletone> singletonExample = new SingletonExample<>();


    public void create(){
        Singletone singletone = singletonExample.getInstance();
    }


}
