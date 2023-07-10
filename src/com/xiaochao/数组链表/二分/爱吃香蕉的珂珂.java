package com.xiaochao.数组链表.二分;

/**
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，
 * 她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 */
public class 爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1 , right = 1000000000 + 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (f(piles,mid) <= h){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
    int f(int[] piles, int x){
        int hours = 0;
        for (int i = 0; i <piles.length ; i++) {
            hours += piles[i] / x ;
            if (piles[i] % x > 0){
                hours++;
            }
        }
        return hours;
    }
}
