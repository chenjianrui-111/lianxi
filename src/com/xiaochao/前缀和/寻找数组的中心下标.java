package com.xiaochao.前缀和;

/**给你一个整数数组 nums ，请计算数组的 中心下标 。
 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和
 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 示例 1：
 输入：nums = [1, 7, 3, 6, 5, 6]
 输出：3
 解释：
 中心下标是 3 。
 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 示例 2：
 输入：nums = [1, 2, 3]
 输出：-1
 解释：
 数组中不存在满足此条件的中心下标。
 示例 3：
 输入：nums = [2, 1, -1]
 输出：0
 解释：
 中心下标是 0 。
 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
 提示：
 1 <= nums.length <= 104
 -1000 <= nums[i] <= 1000
 */

/**
 * 这是一道前缀和的裸题。
 * 只需要用两个数组，前后处理两遍前缀和，再对两个前缀和数组的相同下标进行判别即可。
 * 为了简化数组越界的判断，我们通常会给前缀和数组多预留一位作为哨兵。
 * 这里由于要求前后前缀和。所以我们直接多开两位。
 */
public class 寻找数组的中心下标 {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] s1 = new int[n + 2], s2 = new int[n + 2];
        for (int i = 1; i <= n ; i++) {
            s1[i] = s1[i - 1] + nums[i - 1];
        }
        for (int i = n; i >= 1 ; i--) {
            s2[i] = s2[i - 1] + nums[i - 1];
        }
        for (int i = 1;i <= n;i++){
            if (s1[i] == s2[i])
                //相对nums数组，preSum 数组有一位偏移
                return i - 1;
        }
        return -1;
    }
}
//• 时间复杂度：对数组进行线性扫描。复杂度为 O(n)
//• 空间复杂度：使用了前缀和数组。复杂度为O(n)
/**
 *空间优化（常数级别的优化）
 * 当然，我们也可以只处理一遍前缀和。
 * 然后在判定一个下标是否为”中心索引“的时候，利用前缀和计算左侧值和右侧值。
 * 但这只是常数级别的优化，并不影响其时空复杂度。
 */
class Solution11 {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        for (int i = 1; i <= n; i++) {
            int left = sum[i - 1], right = sum[n] - sum[i];
            if (left == right) return i - 1;
        }
        return -1;
    }
}
        //• 时间复杂度：对数组进行线性扫描。复杂度为 O(n) • 空间复杂度：使用了前缀和数组。复杂度为O(n)
/**
 *空间优化（优化至常数级别）
 * 甚至可以不使用额外空间。
 * 先求一遍总和 total ，再使用 sum 记录当前遍历位置的左侧总和。
 * 对于中心索引必然有： sum = total - sum - nums[i] （左边值 = 右边值）
 */
class Solution12 {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int total = 0, sum = 0;
// 我们的 nums 处理不涉及并行操作，使用循环要比 Arrays.stream 快
// total = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) total += nums[i];
        for (int i = 0; i < n; i++) {
            if (sum == total - sum - nums[i]) return i;
            sum += nums[i];
        }
        return -1;
    }
}
//• 时间复杂度：对数组进行线性扫描。复杂度为 O(n) • 空间复杂度：O(1)
