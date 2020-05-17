package com.zy.java_base.arithmetic.sort.heapify;

import com.zy.java_base.arithmetic.factory.ISortFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.Arrays;

/**
 * 2020/5/14
 */
public class HeapifySort implements ISortFactory {

    private TreeNode<Integer> mRoot;

    @Override
    public int[] sort(int[] arrays) {

        arrays = Arrays.copyOf(arrays, arrays.length);
        createHeapify(arrays);
        sort(mRoot, arrays,arrays.length-1);
        return arrays;
    }
    
    private void sort(TreeNode<Integer> root, int[] arrays,int addr) {

        if (root == null) return;
        arrays[addr] = root.value;

        if(mRoot.weight == 0 && mRoot.left == null){
            mRoot = null;
            return;
        }

        TreeNode<Integer> deleteNode = deleteNode(mRoot);
        TreeNode<Integer> left = root.left;
        TreeNode<Integer> right = root.right;

        deleteNode.parent = root.parent;
        deleteNode.left = left;
        deleteNode.right = right;
        deleteNode.weight = root.weight;
        mRoot = deleteNode;

        if(left != null){
            left.parent = deleteNode;
        }
        if(right != null){
            right.parent = deleteNode;
        }

        fixHeapifyTreeTopToBottom(mRoot);
        sort(mRoot,arrays,addr-1);
    }

    private TreeNode<Integer> deleteNode(TreeNode<Integer> node){

        TreeNode<Integer> parent = node.parent;
        TreeNode<Integer> left = node.left;
        TreeNode<Integer> right = node.right;
        if(node.weight == 0){
            if(right == null){
                if(parent == null){
                    mRoot = null;
                }else {
                    updateNodeStatus(node,false);
                    parent.right = null;
                    node.parent = null;
                }
             return node;
            }
          return  deleteNode(right);
        }else if(node.weight > 0){
            if(right == null){
                updateNodeStatus(left,false);
                node.left = null;
                left.parent = null;

                return left;
            }else if(right.weight == 0) {
               return deleteNode(left);
            }else if(right.weight>0){
              return   deleteNode(right);
            }
       }
        return null;
    }

    private void createHeapify(int[] arrays) {
        for (Integer array : arrays) {
            insertNode(mRoot, createNode(array));
        }
    }

    private TreeNode<Integer> createNode(int data) {
        TreeNode<Integer> node = new TreeNode<>();
        node.value = data;
        return node;
    }

    private void fixHeapifyTreeTopToBottom(TreeNode<Integer> parent) {
        if(parent == null) return;
        TreeNode<Integer> grandParent = parent.parent;
        TreeNode<Integer> left = parent.left;
        TreeNode<Integer> right = parent.right;
        boolean isLeft;
        if(right != null && left != null){
            isLeft = left.compareTo(right.value)>0;
        }else if(right == null){
            isLeft = true;
        }else {
            return;
        }

        boolean sw;
        if(isLeft){
            sw = swap(grandParent,parent,left);
        }else {
           sw = swap(grandParent,parent,right);
        }
        if(sw){
            fixHeapifyTreeTopToBottom(parent);
        }
    }

    private boolean swap(TreeNode<Integer> grandParent,TreeNode<Integer> parent, TreeNode<Integer> node){
        if(parent == null || node == null || parent.compareTo(node.value) > 0) return false;
        node.parent = grandParent;
        if (grandParent != null) {
            if (parent.equals(grandParent.left)) {
                grandParent.left = node;
            } else {
                grandParent.right = node;
            }
        }else {
            mRoot = node;
        }

        TreeNode<Integer> left = node.left;
        TreeNode<Integer> right = node.right;

        parent.parent = node;
        //左孩子
        if(node.equals(parent.left)){
            node.left = parent;
            node.right = parent.right;
            if(parent.right != null){
                parent.right.parent = node;
            }
        }else {
            node.right = parent;
            node.left = parent.left;
            if (parent.left != null){
                parent.left.parent = node;
            }
        }

        parent.left = left;
        parent.right = right;
        if(left != null){
            left.parent = parent;
        }
        if(right!=null){
            right.parent = parent;
        }
        int weight = parent.weight;
        parent.weight = node.weight;
        node.weight = weight;
        return true;
    }

    private void fixHeapifyTreeBottomToTop(TreeNode<Integer> parent, TreeNode<Integer> node) {

        if(parent == null) return;
        TreeNode<Integer> grandParent = parent.parent;
        if (parent.compareTo(node.value) < 0) {
            swap(grandParent,parent,node);
            fixHeapifyTreeBottomToTop(grandParent,node);
        }
    }
    private void updateNodeStatus(TreeNode<Integer> node,boolean isAdd) {
        if(node == null) return;
        TreeNode<Integer> parent = node.parent;
        while (parent != null) {
            if (node.equals(parent.left)) {
                if(isAdd){
                    parent.weight++;
                }else {
                    parent.weight--;
                }

            } else {
                if(isAdd){
                    parent.weight--;
                }else {
                    parent.weight++;
                }

            }
            node = parent;
            parent = parent.parent;
        }
    }

    private void insertLeftNode(TreeNode<Integer> root, TreeNode<Integer> node) {
        while (root.left != null) {
            root = root.left;
        }
        root.left = node;
        node.parent = root;
        updateNodeStatus(node,true);
        fixHeapifyTreeBottomToTop(root,node);
    }

    private void insertRightNode(TreeNode<Integer> root, TreeNode<Integer> node) {

        root.right = node;
        node.parent = root;
        updateNodeStatus(node,true);
        fixHeapifyTreeBottomToTop(root,node);
    }
    /**
     * 完全二叉树
     */
    private void insertNode(TreeNode<Integer> parent, TreeNode<Integer> node) {

        if (mRoot == null) {
            mRoot = node;
        } else {
            if (parent == null) return;

            //左边结点的数量 == 右边结点的数量
            TreeNode<Integer> left = parent.left;
            TreeNode<Integer> right = parent.right;

            if (parent.weight == 0) {
                insertLeftNode(parent, node);
            } else if (parent.weight > 0) {
                if (right == null) {
                    insertRightNode(parent, node);
                } else if (left.weight == 0) {
                    insertNode(right, node);
                } else if (left.weight > 0) {
                    insertNode(left, node);
                }
            }
        }
    }
}
