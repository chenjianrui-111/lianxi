package com.leixing.双指针;

/**
 * 给你一个下标从 0 开始的一维整数数组 original 和两个整数 m 和  n 。你需要使用 original 中 所有 元素创建一个 m 行 n 列的二维数组。
 * original 中下标从 0 到 n - 1 （都 包含 ）的元素构成二维数组的第一行，下标从 n 到 2 * n - 1 （都 包含 ）的元素构成二维数组的第二行，依此类推。
 * 请你根据上述过程返回一个 m x n 的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
 */
public class 将一维数组转变成二维数组 {
    public int[][] construct2DArray(int[] original, int m, int n) {

        if (original.length !=m * n){
            return new int[0][];
        }
        int[][] ans=new int[m][n];
        for (int i=0;i<original.length;i+=n){
            System.arraycopy(original,i,ans[i/n],0,n);
        }
        return ans;
    }
}
