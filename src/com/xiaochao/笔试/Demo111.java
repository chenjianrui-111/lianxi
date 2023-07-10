package com.xiaochao.笔试;

import java.util.Arrays;
/**
 * CVTE朋友在和老师做游戏，他们需要在由整数组成的数字卡片中，找出三个数字，跟老师品出来的目标数字做比较。
 * 三个数字的和与目标数字最按近的那位小朋友就可以得到一个糖里。你可以设计一个程序，帮助小朋友成出最及近的数字的和吗? 题口中的输入可以假设只存在唯一解
 */

/**
 * 对数组进行排序，确保数字按照升序排列。
 * 遍历数组，对于每个数字 nums[i]，使用双指针 left 和 right 分别指向 i+1 和数组末尾。
 * 在 left < right 的条件下，计算 nums[i] + nums[left] + nums[right] 与 target 的差值 diff，并更新最小差值 minDiff。
 * 如果当前和小于目标值，则将 left 右移一位；如果当前和大于目标值，则将 right 左移一位；否则直接返回当前和，因为已经找到最接近的数字和
 */
public class Demo111 {
    public static void main(String[] args) {
        int[] nums = {2,1,-2,3};
        int target = 5;
        int i = closestNumber(nums, target);
        System.out.println(i);
    }
    public static int closestNumber(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int closestSum = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
            }
        }
        return closestSum;
    }

}
