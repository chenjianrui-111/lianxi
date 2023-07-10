package com.xiaochao.归并排序;

/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 */
//count[i] = COUNT(j) where j > i and nums[i] > 2*nums[j]
public class 翻转对 {

    public int reversePairs(int[] nums) {
        // 执⾏归并排序
        sort(nums);
        return count;
    }
    private int[] temp;
    public void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }
    // 归并排序
    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }
    // 记录「翻转对」的个数
    private int count = 0;
    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 在合并有序数组之前，加点私货
//        for (int i = lo; i <= mid; i++) {
//            // 对于左半边的每个 nums[i]，都去右半边寻找符合条件的元素
//            for (int j = mid + 1; j <= hi; j++) {
//                // nums 中的元素可能较⼤，乘 2 可能溢出，所以转化成 long
//                if ((long)nums[i] > (long)nums[j] * 2) {
//                    count++;
//                }
//            }
//        }

        // 进⾏效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 ⼩于 nums[i]
        // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是⼀个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // nums 中的元素可能较⼤，乘 2 可能溢出，所以转化成 long
            while (end <= hi && (long) nums[i] > (long) nums[end] * 2) {
                end++;
            }
            count += end - (mid + 1);
        }
        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
