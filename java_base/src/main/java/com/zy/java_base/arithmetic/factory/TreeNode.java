package com.zy.java_base.arithmetic.factory;



public class TreeNode<T extends Comparable<T>> implements Comparable<T>{


    public TreeNode<T> parent;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public int weight;

    public T value;

    @Override
    public int compareTo(T o) {
        return value.compareTo(o);
    }
}
