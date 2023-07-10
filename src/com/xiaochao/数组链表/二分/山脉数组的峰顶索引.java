package com.xiaochao.数组链表.二分;

/**
 * 符合下列属性的数组 arr 称为 山脉数组：
 * 1、arr.length >= 3
 * 2、存在 i（0 < i < arr.length - 1）使得：arr[0] < arr[1] < ... arr[i-1] < arr[i] 且 arr[i] > arr[i+1] > ... > arr[arr.length - 1]。
 * 给你由整数组成的山脉数组 arr，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i。
 */
public class 山脉数组的峰顶索引 {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0,right =arr.length - 1;
        if (arr.length < 3) return -1;
        // 因为题目必然有解，所以设置 left == right 为结束条件
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // mid 本身就是峰值或其左侧有一个峰值
                right = mid;
            } else {
                // mid 右侧有一个峰值
                left = mid + 1;
            }
        }
        return left;
    }
}
