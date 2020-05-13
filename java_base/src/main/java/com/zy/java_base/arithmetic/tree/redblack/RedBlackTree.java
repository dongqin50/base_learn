package com.zy.java_base.arithmetic.tree.redblack;

import com.zy.java_base.arithmetic.factory.ITreeFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.Arrays;

/**
 * 2020/5/13 花费时间 12小时
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> implements ITreeFactory<T>{

    private RedBlackTreeNode<T> mRoot;


    @Override
    public TreeNode<T> generaTree(T[] array) {
        array = Arrays.copyOf(array,array.length);

        //插入一个结点，默认为红色

        for(T t : array){
            RedBlackTreeNode<T> node = createTreeNode(t);
            insertTreeNode(mRoot,node);
            printTreeNode(mRoot);
            System.out.println(" ");
        }

        return mRoot;
    }


    private void printTreeNode(RedBlackTreeNode<T> root){
        if(root == null) return;
        System.out.print(root.value.toString() +(root.color == RedBlackTreeNode.COLOR_BLACK?"-black":"-red") +" , "   );
        printTreeNode((RedBlackTreeNode<T>) root.left);
        printTreeNode((RedBlackTreeNode<T>) root.right);
    }

//
//    private void printTreeNode(TreeNode root){
//        if(root == null) return;
//        System.out.print(root.value.toString() +" , " );
//        printTreeNode(root.left);
//        printTreeNode(root.right);
//    }

    private RedBlackTreeNode<T> createTreeNode(T value){

        RedBlackTreeNode<T> node = new RedBlackTreeNode<>();

        node.value = value;
        node.color = RedBlackTreeNode.COLOR_RED;

        return node;
    }

    private void insertTreeNode(RedBlackTreeNode<T> root,RedBlackTreeNode<T> node){

        if(root == null){
            mRoot = node;
            node.color = RedBlackTreeNode.COLOR_BLACK;
        }else {
            int com = node.compareTo(root.value);
            if(com == 0) return;
            if(com>0){
                if(root.right != null){
                    insertTreeNode((RedBlackTreeNode<T>) root.right,node);
                }else {
                    root.right = node;
                    node.parent = root;
                    adjustStructure((RedBlackTreeNode<T>) node.parent,node);
                }
            }else {
                if(root.left != null){
                    insertTreeNode((RedBlackTreeNode<T>) root.left,node);
                }else {
                    root.left = node;
                    node.parent = root;
                    adjustStructure((RedBlackTreeNode<T>) node.parent,node);
                }
            }
        }
    }

    private void adjustStructure(
            RedBlackTreeNode<T> parent,
            RedBlackTreeNode<T> node){

        if(parent != null && parent.color == RedBlackTreeNode.COLOR_RED){
            RedBlackTreeNode<T> grandParent = (RedBlackTreeNode<T>) parent.parent;
            boolean isGrandParentLeft = grandParent.compareTo(parent.value) > 0;
            boolean isParentLeft = parent.compareTo(node.value)>0;
            RedBlackTreeNode<T> uncle = (RedBlackTreeNode<T>) (isGrandParentLeft?grandParent.right:grandParent.left);
            if(uncle == null || uncle.color == RedBlackTreeNode.COLOR_BLACK){

                if(isGrandParentLeft){
                    if(isParentLeft){
                        parent.color = RedBlackTreeNode.COLOR_BLACK;
                        grandParent.color = RedBlackTreeNode.COLOR_RED;
                    }else {
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        grandParent.color = RedBlackTreeNode.COLOR_RED;
                        leftRotate(parent);
                    }
                    rightRotate(grandParent);
                }else {

                    if(!isParentLeft){
                        parent.color = RedBlackTreeNode.COLOR_BLACK;
                        grandParent.color = RedBlackTreeNode.COLOR_RED;
                    }else {
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        grandParent.color = RedBlackTreeNode.COLOR_RED;
                        rightRotate(parent);
                    }
                    leftRotate(grandParent);
                }
            }else {
                uncle.color = RedBlackTreeNode.COLOR_BLACK;
                parent.color = RedBlackTreeNode.COLOR_BLACK;
                RedBlackTreeNode<T> grandParentParent = (RedBlackTreeNode<T>) grandParent.parent;
                //判断是不是根结点
                if(grandParentParent != null){
                    grandParent.color = RedBlackTreeNode.COLOR_RED;
                    adjustStructure(grandParentParent,grandParent);
                }
            }
        }
    }
    private void leftRotate(RedBlackTreeNode<T> node){

        if(node == null) return;

        RedBlackTreeNode<T> right = (RedBlackTreeNode<T>) node.right;
        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.parent;

        right.parent = parent;
        if(parent != null){
            int cmp = right.compareTo(parent.value);
            if(cmp < 0){
                parent.left = right;
            }else if(cmp > 0){
                parent.right = right;
            }else {
                return;
            }
        }else {
            mRoot = right;
        }

        node.right = right.left;
        if(right.left != null){
            right.left.parent = node;
        }

        node.parent = right;
        right.left = node;
    }

    private void rightRotate(RedBlackTreeNode<T> node){

        if(node == null) return;

        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.parent;
        RedBlackTreeNode<T> left = (RedBlackTreeNode<T>) node.left;

        left.parent = parent;
        if(parent != null){
            int cmp = left.compareTo(parent.value);
            if(cmp > 0){
                parent.right = left;
            }else if(cmp < 0){
                parent.left = left;
            }else {
                return;
            }
        }else {
            mRoot = left;
        }

        node.left = left.right;
        if(left.right != null){
            left.right.parent = node;
        }

        node.parent = left;
        left.right = node;


    }

    public static class RedBlackTreeNode<T extends Comparable<T>> extends TreeNode<T>{

        final static int COLOR_BLACK = 1;
        final static int COLOR_RED = 2;

        int color;

    }
}
