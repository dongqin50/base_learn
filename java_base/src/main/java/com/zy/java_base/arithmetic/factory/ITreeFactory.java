package com.zy.java_base.arithmetic.factory;

public interface ITreeFactory<T extends Comparable<T>> extends IFactory{

    TreeNode<T> generaTree(T[] array);

}
