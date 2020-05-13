package com.zy.java_base.arithmetic.tree.redblack;

import com.zy.java_base.arithmetic.factory.ITreeFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.Arrays;

public class RedBlackTree<T extends Comparable<T>> implements ITreeFactory<T>{

    private RedBlackTreeNode<T> mRoot;


    @Override
    public TreeNode<T> generaTree(T[] array) {
        array = Arrays.copyOf(array,array.length);

        //插入一个结点，默认为红色

        for(Object t : array){
            RedBlackTreeNode<T> node = createTreeNode((T)t);
            insertTreeNode(mRoot,node);
            printTreeNode(mRoot);
            System.out.println("");
        }

        return mRoot;
    }
//
//    private void d(RedBlackTreeNode<T> node){
//
//        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.parent;
//
//        if(parent != null && parent.color == RedBlackTreeNode.COLOR_RED){
//            //右结点
//            if(node.compareTo(parent.value) > 0){
//
//
//
//            //左结点
//            }else {
//
//            }
//
//
//        }
//    }

    private void printTreeNode(TreeNode root){
        if(root == null) return;
        System.out.print(root.value.toString() +" , " );
        printTreeNode(root.left);
        printTreeNode(root.right);
    }

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
                }
            }else {
                if(root.left != null){
                    insertTreeNode((RedBlackTreeNode<T>) root.left,node);
                }else {
                    root.left = node;
                    node.parent = root;
                }
            }
            adjustStructure((RedBlackTreeNode<T>) node.parent,node);
        }

    }

    private void adjustStructure(RedBlackTreeNode<T> parent,RedBlackTreeNode<T> node){

        if(parent != null && parent.color == RedBlackTreeNode.COLOR_RED){

            if(node.compareTo(parent.value) > 0){
                rightNode(parent,node);
            }else {
                leftNode(parent,node);
            }

        }

    }

    private void leftNode(RedBlackTreeNode<T> node,RedBlackTreeNode<T> nodeLeft){

        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.parent;
        RedBlackTreeNode brother;
        boolean isLeftNode;
        if(parent != null){

            if(parent.compareTo(node.value) > 0){
                isLeftNode = true;
                brother = (RedBlackTreeNode<T>) parent.right;
            }else {
                isLeftNode = false;
                brother = (RedBlackTreeNode<T>) parent.left;
            }

            if(brother != null){

                if(brother.color == RedBlackTreeNode.COLOR_RED){

                    if(!isLeftNode){
                        rightRotate(node);
                        brother.color = RedBlackTreeNode.COLOR_BLACK;
                        nodeLeft.color = RedBlackTreeNode.COLOR_BLACK;
                        node.color = RedBlackTreeNode.COLOR_RED;
                    }else {
                        brother.color = RedBlackTreeNode.COLOR_BLACK;
                        node.color = RedBlackTreeNode.COLOR_RED;
                        nodeLeft.color = RedBlackTreeNode.COLOR_RED;
                    }
                    leftRotate(parent);
                }else {

                    if(isLeftNode){
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
                        rightRotate(parent);
                    }else {
                        rightRotate(node);
                        leftRotate(parent);
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
                    }
                }
            }else {
//                parent.color = RedBlackTreeNode.COLOR_BLACK;
//                root.color = RedBlackTreeNode.COLOR_RED;
                if(isLeftNode){
                    rightRotate(parent);
                    parent.color = RedBlackTreeNode.COLOR_RED;
                    node.color = RedBlackTreeNode.COLOR_BLACK;
                }else {
                    leftRotate(parent);
                    parent.color = RedBlackTreeNode.COLOR_RED;
                    node.color = RedBlackTreeNode.COLOR_BLACK;
                }
            }
            adjustStructure((RedBlackTreeNode<T>) parent.parent,parent);
        }
    }

    private void rightNode(RedBlackTreeNode<T> node,RedBlackTreeNode<T> rightNode){

        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.parent;
        RedBlackTreeNode brother;
        boolean isRightNode;
        if(parent != null){
            if(parent.color == RedBlackTreeNode.COLOR_RED){
                return;
            }
            if(parent.compareTo(node.value) > 0){
                isRightNode = false;
                brother = (RedBlackTreeNode<T>) parent.right;
            }else {
                isRightNode = true;
                brother = (RedBlackTreeNode<T>) parent.left;
            }

            if(brother != null){

                if(brother.color == RedBlackTreeNode.COLOR_RED){

                    if(isRightNode){
                        brother.color = RedBlackTreeNode.COLOR_BLACK;
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
                        leftRotate(parent);
                    }else {
                        leftRotate(node);
                        rightRotate(parent);

                        rightNode.color = RedBlackTreeNode.COLOR_BLACK;
                        brother.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
                        node.color = RedBlackTreeNode.COLOR_RED;
                    }
//
                }else {

                    if(isRightNode){
                        leftRotate(parent);
                        node.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
//                        rightRotate(parent);
//                        node.color = RedBlackTreeNode.COLOR_BLACK;
//                        grandParent.color = RedBlackTreeNode.COLOR_RED;

                    }else {

                        rightRotate(node);
                        leftRotate(parent);

                        rightNode.color = RedBlackTreeNode.COLOR_BLACK;
                        parent.color = RedBlackTreeNode.COLOR_RED;
                        node.color = RedBlackTreeNode.COLOR_RED;
                    }
                }
            }else {
//                parent.color = RedBlackTreeNode.COLOR_BLACK;
//                root.color = RedBlackTreeNode.COLOR_RED;
                if(isRightNode){
                    leftRotate(parent);
                    node.color = RedBlackTreeNode.COLOR_BLACK;
                    parent.color = RedBlackTreeNode.COLOR_RED;
//                        rightRotate(parent);
//                        node.color = RedBlackTreeNode.COLOR_BLACK;
//                        grandParent.color = RedBlackTreeNode.COLOR_RED;

                }else {

                    rightRotate(node);
                    leftRotate(parent);

                    rightNode.color = RedBlackTreeNode.COLOR_BLACK;
                    parent.color = RedBlackTreeNode.COLOR_RED;
                    node.color = RedBlackTreeNode.COLOR_RED;
                }

            }
            adjustStructure((RedBlackTreeNode<T>) parent.parent,parent);
        }

    }

    private void leftRotate(RedBlackTreeNode<T> node){

        if(node == null) return;

        TreeNode right = node.right;
        TreeNode parent = node.parent;

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
            mRoot = (RedBlackTreeNode<T>) right;
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

        TreeNode parent = node.parent;
        TreeNode left = node.left;

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
            mRoot = (RedBlackTreeNode<T>) left;
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
