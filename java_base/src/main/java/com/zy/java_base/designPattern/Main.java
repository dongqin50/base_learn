package com.zy.java_base.designPattern;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        PrintMachine target = new PrintMachine();


        System.out.println(" 前 ...    target : " + target.getClass().hashCode()  );

        Print proxy = (Print) Proxy.newProxyInstance(PrintMachine.class.getClassLoader(),
                PrintMachine.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("前拦截...    target : " + target.getClass().hashCode() + " proxy : " + proxy.getClass().hashCode());

                        Object result = method.invoke(target, args);

                        System.out.println("后拦截...    target : " + target.getClass().hashCode() + " proxy : " + proxy.getClass().hashCode() + " result == null ? " +  (result == null)  +"\n\n" );

                        return result;
                    }
                });


        System.out.println(" 前 ...    proxy : " + proxy.getClass().hashCode()  );

        Field[] fields = proxy.getClass().getDeclaredFields();

        StringBuilder sb = new StringBuilder();
        for(Field f: fields){
            sb.append(f.getName() + " ");
        }

        System.out.println(" Field : " + sb );

        sb = new StringBuilder();
        for(Method method: proxy.getClass().getDeclaredMethods()){
            sb.append(method.getName() + " ");
//            System.out.println(" Method : " + method.getName() +" : " + proxy.getClass().getDeclaredField());
            TypeVariable<Method>[] typeVariables = method.getTypeParameters();
            for(TypeVariable<Method> t:typeVariables){
                System.out.println(method.getName() + " : " +t.getTypeName() + "  " + t.getName() );
            }

        }
        System.out.println(" Method : " + sb+"\n\n");

        proxy.print(1,2);
        proxy.print22("fdfdfdfd");







//        Reflection.getCallerClass();

//        StackTraceElement[] elements = new Throwable().getStackTrace();
//
//        for(int i = 0; i < elements.length; i++){
//
////            if(proxy.getClass().getName().equals(elements[i].getClassName())){
////
////            }
//            System.out.println(" proxy : " + proxy.getClass().getName() +
//                    " className : " + elements[i].getClassName() +
//                      " methodName : " + elements[i].getMethodName());
//        }

    }
}
