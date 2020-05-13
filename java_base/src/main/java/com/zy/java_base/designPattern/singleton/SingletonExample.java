package com.zy.java_base.designPattern.singleton;

public  class SingletonExample<T> {

    private T instance;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }
}
