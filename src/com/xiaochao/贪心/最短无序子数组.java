package com.xiaochao.贪心;

import java.util.Arrays;

/**
 * 双指针 + 排序
 * 最终目的是让整个数组有序，那么我们可以先将数组拷贝一份进行排序，然后使用两个指针 i 和 j 分别找到左右两端第一个不同的地方，那么 [i, j] 这一区间即是答案。
 */
public class 最短无序子数组 {

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        while (i <= j && nums[i] == arr[i]) i++;
        while (i <= j && nums[j] == arr[j]) j--;
        return j - i + 1;
    }
}
//时间复杂度：O(nlogn)
//空间复杂度：O(n)
