package com.leixing.二分;

import java.util.Arrays;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * 朴素解法
 * 如果忽略进阶的 O(log(m+n)) 要求，这道题就非常简单。
 * 一个比较直观的做法：将两个数组合并，排序，然后分别取得 total / 2 和 (total - 1) / 2 两个位置的数，取两者平均值。
 * 这样做的目的是为了避免分情况讨论：合并后的数组长度是奇数还是偶数。
 */
public class 寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        int [] arr=new int[m+n];
        int idx=0;
        for (int i: nums1) arr[idx++] = i;
        for (int i: nums2) arr[idx++] = i;
        Arrays.sort(arr);
        int l=arr[(n+m)/2],r=arr[(n+m-1)/2];
        return (l+r)/2.0;
    }
}
/**
 *时间复杂度：合并两个数组的复杂度是 O(m + n)O(m+n)，对合并数组进行排序的复杂度是 O((m+n)log(m+n))。整体复杂度是 O((m+n)log(m+n))
 * 空间复杂度：O(1)
 */
