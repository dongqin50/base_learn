package com.zy.java_base.designPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("前拦截...    target : " + target.getClass().hashCode() + " proxy : " + proxy.getClass().hashCode());

        Object result = method.invoke(target, args);

        System.out.println("后拦截...    target : " + target.getClass().hashCode() + " proxy : " + proxy.getClass().hashCode() + " result == null ? " +  (result == null)  +"\n\n" );

        return result;
    }
}
