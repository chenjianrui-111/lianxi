package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 根据前序遍历建树输出中序遍历 {
    //节点个数
    private static int size;
    //根节点
    private static Node root;

    public 根据前序遍历建树输出中序遍历() {

    }
    //前序遍历数据创建二叉树（递归）
    private static Node addAllRecur(Integer[] c) {
        Integer var = c[size++];
        if (var == null) {
            return null;
        }
        Node node = new Node(var);
        node.left = addAllRecur(c);
        node.right = addAllRecur(c);
        return node;
    }

    public static Integer[] toArray(int[] arr) {
        //先用LinkedList存储一下，最后再转化为数组
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return null;
        }
        LinkedList<Node> q = new LinkedList<Node>();
        q.addFirst(root);
        Node current;
        while (!q.isEmpty()) {
            current = q.removeLast();
            list.add(current.data);
            if (current.left != null)
                q.addFirst(current.left);
            if (current.right != null)
                q.addFirst(current.right);
        }
        //将list转化为数组
        Integer[] arr1 = new Integer[list.size()];
        arr1= list.toArray(arr1);
        return arr1;
    }

    private static class Node {

        Integer data;
        Node right;
        Node left;

        Node(Integer data) {
            this.data = data;
        }
    }
    public static List<Integer> inorderTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root);
        return list;
    }

    public static void inorder(List list, Node node){
        if (node == null) return;
        inorder(list, node.left);
        list.add(node.data);
        inorder(list, node.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(",");
        int[] arr =new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        Integer[] integers = toArray(arr);
        Node node = addAllRecur(integers);
        List<Integer> list = inorderTraversal(node);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1){
                System.out.print(list.get(i));
            }else {
                System.out.print(list.get(i) + ",");
            }
        }
    }
}
