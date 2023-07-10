package com.xiaochao.笔试;

import com.xiaochao.树.二叉搜索树.后序遍历建树;
import com.xiaochao.树.二叉搜索树.根据后序遍历输出层序遍历;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 现有一棵二叉搜索树，输入为该二叉搜索树的后序遍历结果请根据输入，重建这棵二叉搜索树，并输出这棵树的层序遍历结果
 * 输入[1,3,2]输出[2,1,3]
 */
public class 根据后序遍历建树输出层序遍历 {

    public static class Node{
        int value;
        Node left;
        Node right;
        public  Node(int v){
            value = v;
        }
    }
    public static Node process1(int[] posArr, int L, int R){
        //base case
        if (L > R){
            return null;
        }
        Node head = new Node(posArr[R]);
        //叶子节点
        if (L == R){
            return head;
        }
        //防止只有 < R 或者让 > R 的情况
        int M = L - 1;
        // L < R
        //[L..R-1]找到
        for (int i = L; i < R; i++) {
            if (posArr[i] < posArr[R]){
                M = i;
            }
        }
        head.left = process1(posArr, L, M);
        head.right = process1(posArr, M + 1, R - 1);
        return head;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            String data = line.substring(1,line.length()-1);
            String[] splits = data.split(",");
            int length = splits.length;
            int[] array = new int[length];
            for (int i = 0; i < length ; i++) {
                array[i] = Integer.parseInt(splits[i]);
            }
//            for (int i = 0; i < array.length; i++) {
//                System.out.println(array[i]);
//            }
            Node node = process1(array, 0, array.length-1);
            List<List<Integer>> lists = levelOrder(node);
            int[] array2 = new int[array.length];
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(i).size(); j++) {
                    array2[j] = lists.get(i).get(j).intValue();
                }
            }
            for (int i = 0; i < array2.length; i++) {
                if (i == 0){
                    System.out.println("[" + array2[i] +",");
                }else if (i == array2.length - 1){
                    System.out.println(array2[i] + "]");
                }else {
                    System.out.println(array2[i] + ",");
                }
            }
        }
    }
    //二叉搜索树层序遍历
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size ; i++) {
                Node node = queue.poll();
                level.add(node.value);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(new ArrayList<>(level));
        }
        return res;
    }
}
