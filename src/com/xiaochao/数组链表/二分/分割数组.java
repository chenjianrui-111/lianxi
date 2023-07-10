package com.xiaochao.数组链表.二分;

/**
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * 示例 1：
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */
public class 分割数组 {
    public int splitArray(int[] nums, int m) {
        return shipWithinDays(nums,m);
    }
    public int shipWithinDays(int[] nums, int m){
        int left = 0,right =1;
        for (int num: nums){
            left = Math.max(left,num);
            right+=num;
        }
        while (left < right){
            int mid = left +(right - left) / 2;
            if (f(nums,mid) <= m){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
       return left;
    }
    int f(int[] arr,int x){
        int day = 0;
        for (int i = 0; i <arr.length ; ) {
            int cap = x;
            while (i < arr.length){
                if (cap < arr[i]) break;
                else cap-=arr[i];
                i++;
            }
            day++;
        }
        return day;
    }
}
