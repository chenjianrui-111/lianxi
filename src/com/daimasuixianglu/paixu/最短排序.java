package com.daimasuixianglu.paixu;

import java.util.Arrays;

/**
 * 对于一个无序数组A，请设计一个算法，求出需要排序的最短子数组的长度。
 * 给定一个整数数组A及它的大小n，请返回最短子数组的长度。
 * 测试样例：
 * [1,5,3,4,2,6,7],7
 * 返回：4
 */
public class 最短排序 {
    public int findShortest(int[] A, int n) {
        // write code here
        int[] clone=A.clone();
        Arrays.sort(clone);
        int left=0;
        int right=A.length-1;
        while (left < right && A[left] == clone[left]){
            left++;
        }
        while (right >= 0 && A[right] == clone[right]){
            right--;
        }
        return Math.max(0,right-left+1);
    }
}
