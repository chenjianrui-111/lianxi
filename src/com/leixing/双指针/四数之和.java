package com.leixing.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不
 * 重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * 排序 + 双指针解法
 * 这道题的思路和「15. 三数之和（中等）」、「16. 最接近的三数之和（中等）」类似。
 * 对数组进行排序，使用四个指针 i、j 、k 和 p 分别代表要找的四个数。
 * 通过枚举 i 确定第一个数，枚举 j 确定第二个数，另外两个指针 k 和 p 分别从左边 j + 1 和右边 n - 1 往中间移动，找到满足 nums[i] + nums[j] + nums[k] + nums[p] == t 的所有组合。
 * k 和 p 指针的移动逻辑，分情况讨论 sum = nums[i] + nums[j] + nums[k] + nums[p] ：
 * sum > target：p 左移，使 sum 变小
 * sum < target：k 右移，使 sum 变大
 * sum = target：将组合加入结果集，k 右移继续进行检查
 * 题目要求不能包含重复元素，所以我们要对 i、j 和 k 进行去重，去重逻辑是对于相同的数，只使用第一个。
 */
public class 四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) { // 确定第一个数
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 对第一个数进行去重（相同的数只取第一个）
            for (int j = i + 1; j < n; j++) { // 确定第二个数
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 对第二个数进行去重（相同的数只取第一个）
                // 确定第三个数和第四个数
                int k = j + 1, p = n - 1;
                while (k < p) {

                    // 对第三个数进行去重（相同的数只取第一个）
                    while (k > j + 1 && k < n && nums[k] == nums[k - 1]) k++;
                    // 如果 k 跳过相同元素之后的位置超过了 p，本次循环结束
                    if (k >= p) break;

                    int sum = nums[i] + nums[j] + nums[k] + nums[p];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
                        k++;
                    } else if (sum > target) {
                        p--;
                    } else if (sum < target) {
                        k++;
                    }
                }
            }
        }
        return ans;
    }
}
