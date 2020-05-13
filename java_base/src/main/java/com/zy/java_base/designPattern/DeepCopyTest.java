package com.zy.java_base.designPattern;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 深复制
 */
public class DeepCopyTest {

    public static void main2(String[] a) {
        CloneB b1 = new CloneB();
        b1.aInt = 11;
        System.out.println("before clone,b1.aInt = " + b1.aInt);
        System.out.println("before clone,b1.unCA = " + b1.unCA);

        CloneB b2 = (CloneB) b1.clone();
        b2.aInt = 22;
        b2.unCA.doublevalue();
        System.out.println("=================================");
        System.out.println("after clone,b1.aInt = " + b1.aInt);
        System.out.println("after clone,b1.unCA = " + b1.unCA);
        System.out.println("=================================");
        System.out.println("after clone,b2.aInt = " + b2.aInt);
        System.out.println("after clone,b2.unCA = " + b2.unCA);
    }

    public static Object[] safeCopy(Object[] source, Object[] sink) {
        Class<?> sinkType = null == sink ? Object.class : sink.getClass().getComponentType();
        int sourceLength = null == source ? 0 : source.length;
        int sinkLength = null == sink ? 0 : sink.length;
        ArrayList<Object> result = null;
        int resultSize;
        if (0 == sourceLength) {
            resultSize = 0;
        } else {
            result = new ArrayList(sourceLength);

            for(int i = 0; i < sourceLength; ++i) {
                if (null != source[i] && sinkType.isAssignableFrom(source[i].getClass())) {
                    result.add(source[i]);
                }
            }

            resultSize = result.size();
        }

        if (resultSize != sinkLength) {
            sink = (Object[])((Object[]) Array.newInstance(sinkType, result.size()));
        }

        if (0 < resultSize) {
            sink = result.toArray(sink);
        }

        return sink;
    }


    public static void main(String[] args) {

        List<Person> list = Arrays.asList(
                new Person("aaa", "1111"),
                new Person("aaa1", "2222"),
                new Person("aaa2", "3333"),
                new Person("aaa3", "4444"),
                new Person("aaa4", "5555")
        );


        Object[] list1 = new Person[list.size()];
//        System.arraycopy(list.toArray(), 0, list1, 0, list.size());
        list1 = safeCopy(list.toArray(),list1);
        list.stream()
                .forEach(System.out::println);
        System.out.println("---------------------------------------");
        Arrays.asList(list1).stream()
                .forEach(System.out::println);

        System.out.println("**********************************************");

        ((Person)list1[0]).name = "bbbb";

        list.stream()
                .forEach(System.out::println);
        System.out.println("---------------------------------------");
        Arrays.asList(list1).stream()
                .forEach(System.out::println);

    }
}

final class Person implements Cloneable {
    String name;
    String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name : " + name + " age : " + age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class UnCloneA {
    private int i;

    public UnCloneA(int ii) {
        i = ii;
    }

    public void doublevalue() {
        i *= 2;
    }

    public String toString() {
        return Integer.toString(i);
    }
}

class CloneB implements Cloneable {
    public int aInt;
    public UnCloneA unCA = new UnCloneA(111);

    public Object clone() {
        CloneB o = null;
        try {
            o = (CloneB) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}