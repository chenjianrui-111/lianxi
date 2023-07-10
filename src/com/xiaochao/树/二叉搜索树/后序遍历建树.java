package com.xiaochao.树.二叉搜索树;

public class 后序遍历建树 {

    public static class Node{
        int value;
        Node left;
        Node right;
        public  Node(int v){
            value = v;
        }
    }
    public static Node posArrayToBST1(int[] posArr){
        return process1(posArr,0,posArr.length - 1);
    }
    public static Node process1(int[] posArr,int L,int R){
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

    public static Node process2(int[] posArr,int L,int R){
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
        int M = -1;
        // L < R
        //[L..R-1]找到
        for (int i = L; i < R; i++) {
            if (posArr[i] < posArr[R]){
                M = i;
            }
        }
        if (M == -1){
            head.right = process2(posArr, L, R - 1);
        }else if (M == R - 1){
            head.left = process2(posArr, L, R - 1);
        }else {
            head.left = process2(posArr, L, M);
            head.left = process2(posArr, M + 1, R - 1);
        }
        return head;
    }

    //二分
    public static Node process3(int[] posArr,int L,int R){
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
        head.left = process3(posArr, L, M);
        head.right = process3(posArr, M + 1, R - 1);
        return head;
    }
}

