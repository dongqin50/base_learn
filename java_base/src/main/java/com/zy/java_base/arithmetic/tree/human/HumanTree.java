package com.zy.java_base.arithmetic.tree.human;

import com.zy.java_base.arithmetic.factory.ITreeFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HumanTree<T extends Comparable<T>> implements ITreeFactory<T> {

    
    @Override
    public TreeNode<T> generaTree(T[] array) {
        
        List<TreeNode<T>> list = transformToList(array);
        mergerSort(list,0,list.size()-1);
        humanTree(list);
        return list.get(0);
    }



    private void insertSort(List<TreeNode<T>> list,TreeNode<T> node){
        for (int i = 0; i < list.size(); i++) {
            TreeNode<T> n = list.get(i);
            if(node.weight <= n.weight){
                list.add(i,node);
                return;
            }
        }
        list.add(list.size(),node);
    }

    private void humanTree(List<TreeNode<T>> list){

        while (list.size()>1){
            TreeNode<T> left = list.get(0);
            TreeNode<T> right = list.get(1);

            TreeNode<T> parent = new TreeNode<>();
            parent.weight = left.weight + right.weight;
            parent.left = left;
            parent.right = right;

            left.parent = parent;
            right.parent = parent;

            insertSort(list,parent);



            list.remove(left);
            list.remove(right);
        }
    }

    private List<TreeNode<T>> transformToList(T[] array){

        Map<T,TreeNode<T>> map = new HashMap<>();

        for (T key : array){
            TreeNode<T> node = map.get(key);
            if(node == null){
                node = new TreeNode<>();
                node.value = key;
                map.put(key, node);
            }
            node.weight++;
        }
        List<TreeNode<T>> list = new ArrayList<>();

        for(Map.Entry<T,TreeNode<T>> node : map.entrySet()){
            list.add(node.getValue());
        }
        return list;
    }

    private void mergerSort(List<TreeNode<T>> datas,int l , int h) {
        
        if(l >= h) return;
        
        int mid = (l + h) /2;
        mergerSort(datas,l,mid);
        mergerSort(datas,mid+1,h);
        sort(datas,l,mid,h);
    }
    
    private void sort(List<TreeNode<T>> datas,int l,int m,int h){
        
        if(l >= h) return;
        
        int leftSize = m - l + 1;
        int rightSize = h-m;
        
        List<TreeNode<T>> leftArray = new ArrayList<>(leftSize);
        List<TreeNode<T>> rightArray = new ArrayList<>(rightSize);
        
        for(int i = 0; i < leftSize;i++){
            leftArray.add(datas.get(i+l));
        }
        
        for(int i = 0; i < rightSize; i++){
            rightArray.add(datas.get(m+i+1));
        }
        
        int ll = 0,rr = 0,dd = l;
        
        while (ll < leftArray.size()&&rr<rightArray.size()){
            if(leftArray.get(ll).weight < rightArray.get(rr).weight){
                datas.set(dd++,leftArray.get(ll++));
            }else {
                datas.set(dd++,rightArray.get(rr++));
            }
        }
        
        while (ll < leftSize){
            datas.set(dd++,leftArray.get(ll++));
        }
        
        while (rr < rightSize){
            datas.set(dd++,rightArray.get(rr++));
        }
        
    }

}
