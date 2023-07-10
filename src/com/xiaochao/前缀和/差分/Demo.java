package com.xiaochao.前缀和.差分;

import java.util.Arrays;

/**
 * 差分标记：
 * 1.[L,R] + v == d[L] +V, d[R + 1] - V
 * 2.把标记后的 D 差分数组进行一次前缀和操作
 * 适用于多次操作单词询问
 * 时间复杂度O(M+N)
 */
public class Demo {
    private static int[] d;
    public static void main(String[] args) {
        int[] arr ={1,3,7,5,2};
         d = new int[6];
        Arrays.fill(d,0);
        add(2,4,5);
        add(1,3,2);
        add(0,2,-3);
        for (int i = 1;i < 5;i++) d[i] += d[i-1];
        for (int i = 0; i < 5; i++) {
            arr[i] += d[i];
            System.out.print(arr[i] + " ");
        }
    }
    static void add(int l,int r,int value){
        d[l] += value;
        d[r+1] -= value;
    }
}
