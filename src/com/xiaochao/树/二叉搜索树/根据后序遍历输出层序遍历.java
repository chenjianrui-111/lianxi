package com.xiaochao.树.二叉搜索树;

import java.util.*;

public class 根据后序遍历输出层序遍历 {
    public static class Node{
        int value;
        Node left;
        Node right;
        public  Node(int v){
            value = v;
        }
    }
    public static int[] printResult (int[] arr) {
        // write code here
        int n =arr.length;
        int[] arr2 = new int[n];
        Node node = process2(arr, 0, arr.length - 1);
        List<List<Integer>> lists = levelOrder(node);
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j <lists.get(i).size(); j++) {
                arr2[j] = lists.get(i).get(j).intValue();
            }
        }
        return arr2;
    }
    public static Node process2(int[] posArr, int L, int R){
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
        int left = L;
        int right = R - 1;
        while (left <= right){
            int mid = left +((right - left) >> 1);
            if (posArr[mid] < posArr[R]){
                M = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        head.left = process2(posArr, L, M);
        head.right = process2(posArr, M + 1, R - 1);
        return head;
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

    public static void main(String[] args) {
        int[] arr = {1,3,2};
        int[] ints = printResult(arr);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
    }
}
