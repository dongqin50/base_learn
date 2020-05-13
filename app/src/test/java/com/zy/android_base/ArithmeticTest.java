package com.zy.android_base;


import com.zy.java_base.arithmetic.factory.ArithmeticFactory;
import com.zy.java_base.arithmetic.factory.TreeNode;
import com.zy.java_base.arithmetic.select.OrderSelect;
import com.zy.java_base.arithmetic.select.binary.BinarySelect;
import com.zy.java_base.arithmetic.select.binary.BinarySelect1;
import com.zy.java_base.arithmetic.sort.bubble.BubbleSort2;
import com.zy.java_base.arithmetic.sort.counter.CounterSort1;
import com.zy.java_base.arithmetic.sort.insert.InsertSort2;
import com.zy.java_base.arithmetic.sort.merger.MergerSort2;
import com.zy.java_base.arithmetic.sort.quick.QuickSort4;
import com.zy.java_base.arithmetic.sort.select.SelectSort2;
import com.zy.java_base.arithmetic.sort.shell.ShellSort2;
import com.zy.java_base.arithmetic.tree.avl.AviTree1;
import com.zy.java_base.arithmetic.tree.avl.AvlTree0;
import com.zy.java_base.arithmetic.tree.human.HumanTree;
import com.zy.java_base.arithmetic.tree.redblack.RedBlackTree;
import com.zy.java_base.utils.PrintUtils;
import com.zy.java_base.utils.RandomUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testBubbleSort() throws Exception {

//        int[] arrays = RandomUtils.createRandomArray(10);
        int[] arrays = {9,4,2,7,5,6,0,3};
        long start = System.currentTimeMillis();
        int[] quickValues = ArithmeticFactory.createClass(BubbleSort2.class).sort(arrays); //0-8
        PrintUtils.println(" --------- BubbleSort ------ time : " + (System.currentTimeMillis() - start));
        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays),PrintUtils.println(quickValues));
    }

    @Test
    public void testQuickSort() {
        int[] arrays = RandomUtils.createRandomArray(1000000);
        long start = System.currentTimeMillis();
        int[] quickValues = ArithmeticFactory.createClass(QuickSort4.class).sort(arrays); //0-8

        PrintUtils.println(" --------- QuickSort ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(quickValues));
    }

    @Test
    public void testMergerSort() {
//        int[] arrays = RandomUtils.createRandomArray(10);
        int[] arrays = { 49, 38, 65, 97, 76, 13, 27, 50 };
        long start = System.currentTimeMillis();
        int[] quickValues = ArithmeticFactory.createClass(MergerSort2.class).sort(arrays); //0-8

        for(int a : arrays){
            System.out.print(a+",");
        }
        System.out.println("");

        PrintUtils.println(" --------- MergerSort ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(quickValues));
    }

    @Test
    public void testInsertSort() {
        int[] arrays = RandomUtils.createRandomArray(10);
        long start = System.currentTimeMillis();
        int[] quickValues = ArithmeticFactory.createClass(InsertSort2.class).sort(arrays); //0-8

        PrintUtils.println(" --------- MergerSort ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(quickValues));
    }

    @Test
    public void testNumber(){

        int interval = 5;
        int a = interval >> 1;
        System.out.println("value = " + a);

    }

    @Test
    public void testShellSort() {
        int[] arrays = RandomUtils.createRandomArray(100);
        long start = System.currentTimeMillis();
        int[] quickValues = ArithmeticFactory.createClass(ShellSort2.class).sort(arrays); //0-8

        PrintUtils.println(" --------- ShellSort ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(quickValues));
    }

    @Test
    public void testCounterSort() {
        int[] arrays = RandomUtils.createRandomArray(10000000);
//        int[] arrays = {-3,-9,6,4,2,9,2,1,7,5,8,-1,-1,-3,-6,8,3};
        long start = System.currentTimeMillis();
//        int[] quickValues = ArithmeticFactory.createClass(CounterSort.class).sort(arrays); //0-8
        int[] quickValues = ArithmeticFactory.createClass(CounterSort1.class).sort(arrays); //0-8

        PrintUtils.println(" --------- CounterSort ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(quickValues));
    }


    @Test
    public void testSelectSort() throws Exception {

//        Arrays.sort();

        int[] arrays = RandomUtils.createRandomArray(10);

        long start = System.currentTimeMillis();
        int[] selectSort = ArithmeticFactory.createClass(SelectSort2.class).sort(arrays);

        PrintUtils.println(" --------- SelectSort1 ------ time : " + (System.currentTimeMillis() - start));

        Arrays.sort(arrays);
        assertEquals(PrintUtils.println(arrays), PrintUtils.println(selectSort));

    }

    @Test
    public void testSelectOrder() throws Exception {

        int[] arrays = RandomUtils.createRandomArray(10000000);

        int num = arrays[1000442];

        long start = System.currentTimeMillis();
        OrderSelect orderSelect = ArithmeticFactory.createClass(OrderSelect.class);
        System.out.println("--------- OrderSelect ---------- " + (System.currentTimeMillis() - start) + " result : " + orderSelect.select(arrays, num));


        start = System.currentTimeMillis();
        BinarySelect select = ArithmeticFactory.createClass(BinarySelect.class);
        System.out.println("--------- BinarySelect ---------- " + (System.currentTimeMillis() - start) + " result : " + select.select(arrays, num));

    }

    @Test
    public void testBinarySelect() throws Exception {

        int[] arrays = RandomUtils.createRandomArray(10000000);
        int num = arrays[1000];
       long start = System.currentTimeMillis();
        BinarySelect1 select = ArithmeticFactory.createClass(BinarySelect1.class);
        System.out.println("--------- BinarySelect ---------- " +
                (System.currentTimeMillis() - start) + " result : " + select.select(arrays, num));

    }

    @Test
    public void testAvlOrder() throws Exception {

        int[] arrays = {9,4,2,7,5,6,0,3};
        Integer[] array = {9,4,2,7,5,6,0,3};
//        int[] arrays = RandomUtils.createRandomArray(10);

        int num = arrays[0];

        long start = System.currentTimeMillis();
        AvlTree0 aviSelect = ArithmeticFactory.createClass(AvlTree0.class);
        aviSelect.select(arrays, num);


        AviTree1<Integer> aviSelect1 = new AviTree1<>();

        aviSelect1.generaTree(array);
//        printTreeNode(aviSelect1.generaTree(array));

        System.out.println("\n--------- AVISelect ---------- " + (System.currentTimeMillis() - start) + " result : "  );

    }

    public class DataInteger implements Comparable<DataInteger> {

        int value;
        String data;

        public DataInteger(int value) {
            this.value = value;
        }

        public DataInteger() {
        }


        @Override
        public int compareTo( DataInteger o) {
            if(value == o.value) return 0;
            if(value > o.value) return 1;
            return -1;
        }


        @Override
        public String toString() {
            return ""+value;
        }
    }

    @Test
    public void testRedBlackTreeOrder() throws Exception {


        DataInteger[] arrays = {
                new DataInteger(9),
                new DataInteger(4),
                new DataInteger(2),
                new DataInteger(7),
                new DataInteger(5),
                new DataInteger(6),
                new DataInteger(0),
                new DataInteger(3)};
//        int[] arrays = RandomUtils.createRandomArray(10);

//        int num = arrays[0];

        long start = System.currentTimeMillis();
        RedBlackTree<DataInteger> tree = new RedBlackTree<>();
        tree.generaTree(arrays);
        System.out.println("\n--------- RedBlackTreeOrder ---------- " + (System.currentTimeMillis() - start)  );

    }


    @Test
    public void testHumanTreeOrder(){
        Integer[] arrays = { 49, 38, 65, 97, 76, 13, 27, 50 ,8,7,5,4,8,6,49,65,78,46,23,0,89,77,56,76,87,78,87,56,67,87};

        HumanTree<Integer> tree = new HumanTree<>();
        TreeNode<Integer> node = tree.generaTree(arrays);
        printTreeNode(node);

    }

    @Test
    public void test() {

    }


    private void printTreeNode(TreeNode root){
        if(root == null) return;

        System.out.print(root.value +" , " );
        printTreeNode(root.left);
        printTreeNode(root.right);
    }

}
