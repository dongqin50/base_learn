package com.zy.java_base.arithmetic.tree.avl;

import com.zy.java_base.arithmetic.factory.ISelectFactory;

import java.util.Arrays;


/**
 * 2020/5/2  花费时间10小时
 *
 * 问题：
 *
 *
 *
 */
public class AvlTree0 implements ISelectFactory {

    private TreeNode mRoot;

    private final int LH = 1;
    private final int RH = -1;
    private final int EQ = 0;

    @Override
    public boolean select(int[] arrays, int num) {

        arrays = Arrays.copyOf(arrays,arrays.length);

        //  按照二叉树插入数据
        generaTreeNode(arrays);

        printTreeNode(mRoot);

        return selectTreeNode(mRoot,num);
    }

    private void printTreeNode(TreeNode root){
        if(root == null) return;
        System.out.print(root.value +" , " );
        printTreeNode(root.left);
        printTreeNode(root.right);
    }

    private void generaTreeNode(int[] array){
        for (int num : array){
           TreeNode node =  createTreeNode(num);
           insertTreeNode(mRoot,node);
           balanceTree(node);
        }
    }

    private TreeNode createTreeNode(int value){
        TreeNode node = new TreeNode();
        node.value = value;
        return node;
    }


    private void insertTreeNode(TreeNode root,TreeNode node) {

        if(mRoot == null){
             root = node;
             mRoot = root;
        }else {
            if(node.value>root.value){
                if(root.right == null){
                    root.right = node;
                    node.parent = root;
                    return;
                }else {
                    insertTreeNode(root.right,node);
                }
            }else if(node.value < root.value){
                if(root.left == null){
                    root.left = node;
                    node.parent = root;
                    return;
                }else {
                    insertTreeNode(root.left,node);
                }
            }
        }
    }

    private void balanceTree(TreeNode node){
        TreeNode root = getTopBalanceTree(node);
        if(root.equals(node))return;
        switch (root.balance){
            //左平衡树
            case 2:
                leftBalanceTree(root);
                break;
            //右平衡树
            case -2:
                rightBalanceTree(root);
                break;
        }
    }


    private TreeNode getTopBalanceTree(TreeNode node) {
       TreeNode current = node;

        while (current!= null&&Math.abs(current.balance)<2){
            TreeNode parent = current.parent;
            if(parent == null){
                return current;
            }

            if(node.value > parent.value ){
                parent.balance--;
            }else {
                parent.balance++;
            }

            current = parent;
        }
        return current;
    }

//    private int countBalance(TreeNode root,TreeNode node){
//
//        if(node.value > root.value){
//            root.balance--;
//        }else {
//            root.balance++;
//        }
//
//        if(node.left != null){
//            node.left.balance = 1+countBalance(node.left);
//        }
//
//        if(node.right!=null){
//            node.balance = countBalance(node.right)-1;
//        }
//
//        return node.balance;
//    }
//


    //左平衡树
    private void leftBalanceTree(TreeNode root) {

        TreeNode leftSon = root.left;
        switch (leftSon.balance) {
            case LH:
                rightRotate(root);
                root.balance = EQ;
                leftSon.balance = EQ;
                break;
            case RH:
                TreeNode rightChildNode = leftSon.right;
                leftRotate(leftSon);

//                printTreeNode(mRoot);
//                System.out.println("");

                rightRotate(root);
                switch (rightChildNode.balance){
                    case LH:
                        rightChildNode.balance=EQ;
                        leftSon.balance=EQ;
                        root.balance=RH;
                        break;
                    case RH:
                        rightChildNode.balance=EQ;
                        leftSon.balance=LH;
                        root.balance=EQ;
                        break;
                    case EQ:
                        rightChildNode.balance=EQ;
                        leftSon.balance=EQ;
                        root.balance=EQ;
                        break;
                }
                break;
        }

//        printTreeNode(mRoot);
//        System.out.println("");
        //左子树上 -> 右旋

//        rightRotate(node);


        //左子树上的右子树 -> 左旋 -> 右旋
        //1.LH

        //2.RH

        //3.EQ
    }

    //右平衡树
    private void rightBalanceTree(TreeNode root) {

        TreeNode rightSon = root.right;
        switch (rightSon.balance) {
            case RH:
                leftRotate(root);
                break;
            case LH:
                TreeNode leftChildNode = rightSon.left;

                rightRotate(rightSon);
                leftRotate(root);

                switch (leftChildNode.balance){
                    case RH:
                        leftChildNode.balance=EQ;
                        root.balance=EQ;
                        rightSon.balance=LH;
                        break;
                    case LH:

                        rightSon.balance=EQ;
                        leftChildNode.balance=RH;
                        root.balance=EQ;

                        break;
                    case EQ:

                        rightSon.balance=EQ;
                        leftChildNode.balance=EQ;
                        root.balance=EQ;
                        break;
                }
                break;

        }
//        printTreeNode(mRoot);
//        System.out.println("");
        //右子树上 -> 左旋

        //右子树上的左子树 ->  右旋 -> 左旋

        //1.LH

        //2.RH

        //3.EQ
    }


    private void leftRotate(TreeNode root) {

        TreeNode right = root.right;
        TreeNode parent = root.parent;

        //right -> root
        right.parent = parent;
        if (parent != null) {
            if (parent.left == root) {
                parent.left = right;
            } else {
                parent.right = right;
            }
        } else {
            mRoot = right;
        }
        //right.left -> root.right
        root.right = right.left;
        if (right.left != null) {
            right.left.parent = root;
        }

        //root -> right.left
        root.parent = right;
        right.left = root;

    }

    private void rightRotate(TreeNode root) {

        TreeNode parent = root.parent;
        TreeNode left = root.left;

        //left -> root

        left.parent = parent;
        if (parent != null) {
            if (root.equals(parent.left)) {
                parent.left = left;
            } else {
                parent.right = left;
            }
        } else {
            mRoot = left;
        }

        //left.right -> root.left

        root.left = left.right;
        if (left.right != null) {
            left.right.parent = root;
        }

        //root -> left.right

        root.parent = left;
        left.right = root;

    }

    private boolean selectTreeNode(TreeNode root,int value) {
        if(root != null){
            if(root.value > value){
                boolean isExist = selectTreeNode(root.right,value);
                if(isExist){
                    return true;
                }
            }else if(root.value == value){
                return true;
            } else {
                boolean isExist = selectTreeNode(root.left,value);
                if(isExist){
                    return true;
                }
            }
        }
        return false;
    }

    public static class TreeNode {

        int value;
        int balance;
        int weight;

        TreeNode parent;
        TreeNode left;
        TreeNode right;

    }


}
