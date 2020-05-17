package com.zy.java_base.arithmetic.tree.binary;

import com.zy.java_base.arithmetic.factory.ITreeFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.Arrays;

/**
 * 完全二叉树
 * 2020/5/14
 *
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>> implements ITreeFactory<T> {

    @Override
    public TreeNode<T> generaTree(T[] arrays) {
        arrays = Arrays.copyOf(arrays, arrays.length);

        createBinaryTree(arrays);

        return mRoot;
    }

    private TreeNode<T> mRoot;

    private void createBinaryTree(T[] arrays) {
        for (T array : arrays) {
            insertNode(mRoot, createNode(array));
        }
    }

    private TreeNode<T> createNode(T data) {
        TreeNode<T> node = new TreeNode<>();
        node.value = data;
        return node;
    }


    private void insertLeftNode(TreeNode<T> root, TreeNode<T> node) {

        while (root.left != null) {
            root = root.left;
        }
        root.left = node;
        node.parent = root;
        updateNodeStatus(node);

    }

    private void updateNodeStatus(TreeNode<T> node) {

        TreeNode<T> parent = node.parent;
        while (parent != null) {
            if (node.equals(parent.left)) {
                parent.weight++;
            } else {
                parent.weight--;
            }
            node = parent;
            parent = parent.parent;
        }
    }

    private void insertRightNode(TreeNode<T> root, TreeNode<T> node) {
        root.right = node;
        node.parent = root;
        updateNodeStatus(node);
    }

    private void insertNode(TreeNode<T> parent, TreeNode<T> node) {

        if (mRoot == null) {
            mRoot = node;
        } else {
            if (parent == null) return;

            //左边结点的数量 == 右边结点的数量
            TreeNode<T> left = parent.left;
            TreeNode<T> right = parent.right;

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
