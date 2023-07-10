package com.leixing.滑动窗口;

import java.util.Arrays;

/**
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；
 * 此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，
 * 每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，
 * 并输出由它们组成的数组。
 */
public class 滑动窗口中位数 {
    public double[] medianSlidingWindow(int[] nums, int k) {
      int len=nums.length;
      int cnt=len-k+1;
      double[] ans=new double[cnt];
      int [] t=new int[k];
      for (int l=0,r=l+k-1;r<len;l++,r++){
          for (int i=1;i<=r;i++) {
              t[i-l]=nums[i];
          }
          Arrays.sort(t);
          ans[l]=(t[k/2]/2.0)+(t[(k-1)/2]/2.0);
      }
      return ans;
    }
}
/**
 *时间复杂度：最多有 n 个窗口需要滑动计算。每个窗口，需要先插入数据，复杂度为 O(k)，
 * 插入后需要排序，复杂度为 O(klogk)。整体复杂度为 O(n∗(k+klogk))
 * 空间复杂度：使用了长度为 k 的临时数组。复杂度为 O(k)
 */
