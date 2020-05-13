package com.zy.java_base.arithmetic.tree.avl;

import com.zy.java_base.arithmetic.factory.ITreeFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.Arrays;

public class AviTree1<T extends Comparable<T>> implements ITreeFactory<T> {

    private TreeNode<T> mRoot;

    private final int EQ = 0;
    private final int EL = 1;
    private final int ER = -1;


    @Override
    public TreeNode<T> generaTree(T[] arrays) {
        arrays = Arrays.copyOf(arrays,arrays.length);
        for (T t : arrays){
            insertTree(mRoot,t);
            printTreeNode(mRoot);
            System.out.println("");
        }
        return mRoot;
    }

    private void insertTree(TreeNode<T> parent,T data){

        TreeNode<T> node = createTreeNode(data);
        if(mRoot == null){
            mRoot = node;
        }else {
            int com = parent.compareTo(data);
            if(com == 0) return;
            if(com > 0){
                if(parent.left != null){
                    insertTree(parent.left,data);
                }else {
                    parent.left = node;
                    node.parent = parent;

                    adjustTreeStruct(node);
                }
            }else {
                if(parent.right != null){
                    insertTree(parent.right,data);
                }else {
                    parent.right = node;
                    node.parent = parent;
                    adjustTreeStruct(node);
                }
            }
        }
    }

    private void printTreeNode(TreeNode root){
        if(root == null) return;

        System.out.print(root.value +" , " );
        printTreeNode(root.left);
        printTreeNode(root.right);
    }


    private TreeNode<T> getBalanceRootNode(TreeNode<T> node){

        TreeNode<T> parent = node.parent;
        while (parent != null){
            if(parent.compareTo(node.value) > 0){
                parent.weight++;
            }else {
                parent.weight--;
            }
            if(Math.abs(parent.weight)>=2){
                break;
            }
            parent = parent.parent;
        }

        return parent;
    }

    private void adjustTreeStruct(TreeNode<T> node){
        TreeNode<T> parent = getBalanceRootNode(node);
        if(parent == null) return;
        switch (parent.weight){
            case 2:
                leftAdjust(parent);
                break;
            case -2:
                rightAdjust(parent);
                break;
        }

    }




    private void leftAdjust(
            TreeNode<T> parent){
        TreeNode<T> left = parent.left;
        TreeNode<T> right = parent.right;


        //1.parent 一个子节点   parent left lLeft 结点  -> parent右旋    parent.weight = left.weight = 0
        //2.parent 一个子节点   parent left lRight 结点  -> 先right左旋后parent右旋   parent.weight = left.weight = 0

        //3.parent 两个子节点  left 两个子节点（注意以parent为根，且parent有两个结点时，left必须只有2个结点，否则parent就会下移为left）
        //3.1 left.weight > 0   -> parent右旋 parent.weight = left.weight = 0;

        //3.2 left.weight < 0  leftChild.weight > 0  -> 先left左旋 后parent右旋  lRight.weight = left.weight = 0   parent.weight=-1
        //3.3 left.weight < 0  leftChild.weight < 0  -> 先left左旋 后parent右旋  lRight.weight = parent.weight = 0   left.weight=1


        if(right == null || left.weight >0){
            if(left.weight < 0){
                leftRotate(left);
            }
            rightRotate(parent);
            parent.weight = EQ;
            left.weight = EQ;

        }else {
            TreeNode<T> leftChild = left.right;
            if(leftChild.weight > 0){
                parent.weight = ER;
                leftChild.weight = EQ;
                left.weight = EQ;
            }else {
                parent.weight = EQ;
                left.weight = EL;
                leftChild.weight = EQ;
            }
            leftRotate(left);
            rightRotate(parent);
        }
    }

    private void rightAdjust(
            TreeNode<T> parent){
        TreeNode<T> right = parent.right;
        TreeNode<T> left = parent.left;

        if(left == null || right.weight<0){
            if(right.weight > 0){
                rightRotate(right);
            }
            leftRotate(parent);
            parent.weight = EQ;
            right.weight = EQ;
        }else {
            TreeNode<T> rightChild = right.left;
            if(rightChild.weight < 0){
                parent.weight = ER;
                right.weight = EQ;
                rightChild.weight = EQ;
            }else {
                parent.weight = EQ;
                right.weight = EL;
                rightChild.weight = EQ;
            }
            rightRotate(right);
            leftRotate(parent);
        }

    }

    private TreeNode<T> createTreeNode(T data){
        TreeNode<T> node = new TreeNode<>();
        node.value = data;
        node.weight=0;
        return node;
    }

    private void leftRotate(TreeNode<T> root){

        TreeNode<T> parent = root.parent;
        TreeNode<T> right = root.right;
        TreeNode<T> rLeft = right.left;

        right.parent = parent;
        if(parent != null){
            if(parent.compareTo(right.value)>0){
                parent.left = right;
            }else {
                parent.right = right;
            }
        }else {
            mRoot = right;
        }
        root.right = rLeft;
        if(rLeft != null){
            rLeft.parent = parent;
        }

        root.parent = right;
        right.left = root;
    }

    private void rightRotate(TreeNode<T> root){

        TreeNode<T> parent = root.parent;
        TreeNode<T> left = root.left;
        TreeNode<T> lRight = left.right;

        left.parent = parent;
        if(parent != null){
            if(parent.compareTo(left.value)>0){
                parent.left = left;
            }else {
                parent.right = left;
            }
        }else {
            mRoot = left;
        }
        root.left = lRight;
        if(lRight != null){
            lRight.parent = root;
        }

        root.parent = left;
        left.right = root;
    }
}
