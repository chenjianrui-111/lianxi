package com.leixing.滑动窗口;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * 提示：
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * 滑动窗口 + 哈希表
 * 整理题意：是否存在长度不超过的 k + 1 窗口，窗口内有相同元素。
 * 我们可以从前往后遍历 nums，同时使用 Set 记录遍历当前滑窗内出现过的元素。
 * 假设当前遍历的元素为 nums[i]：
 * 下标小于等于 k（起始滑窗长度还不足 k + 1）：直接往滑窗加数，即将当前元素加入 Set 中；
 * 下标大于 k：将上一滑窗的左端点元素 nums[i - k - 1]移除，判断当前滑窗的右端点元素 nums[i]是否
 * 存在 Set 中，若存在，返回 True，否则将当前元素 nums[i] 加入 Set 中。
 * 重复上述过程，若整个 nums 处理完后仍未找到，返回 False。
 * @author CJR
 */
public class 存在重复元素2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len=nums.length;
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<len;i++){
            if (i > k){
                set.remove(nums[i-k-1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
