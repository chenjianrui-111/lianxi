package com.xiaochao.DP.数位dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 * 示例 1：
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 */

/**
 * 由于题目给定的 digits 不包含 0，因此相当于只需要回答使用 digits 的数值能够覆盖 [1, x] 范围内的多少个数字。
 * 起始先将字符串数组 digits 转为数字数组 nums，假定 nums 的长度为 mm，然后考虑如何求得 [1, x] 范围内合法数字的个数。
 * 假定我们存在函数 int dp(int x) 函数，能够返回区间 [1, x] 内合法数的个数，那么配合「容斥原理」我们便能够回答任意区间合法数的查询：
 * ans_{(l, r)} = dp(r) - dp(l - 1)
 * 对于本题，查询区间的左端点固定为 1，同时 dp(0) = 0，因此答案为 dp(x)。
 * 然后考虑如何实现 int dp(int x) 函数，我们将组成 [1, x] 的合法数分成三类：
 * 位数和 x 相同，且最高位比 x 最高位要小的，这部分统计为 res1；
 * 位数和 x 相同，且最高位与 x 最高位相同的，这部分统计为 res2；
 * 位数比 x 少，这部分统计为 res3。
 * 其中 res1 和 res3 求解相对简单，重点落在如何求解 res2 上。
 * 对 x 进行「从高到低」的处理（假定 x 数位为 n），对于第 k 位而言（k 不为最高位），假设在 xx 中第 k 位为 cur，那么为了满足「大小限制」关系，我们只能在 [1, cur - 1]范围内取数，同时为了满足「数字只能取自 nums」的限制，因此我们可以利用 nums 本身有序，对其进行二分，找到满足 nums[mid] <= cur 的最大下标 rr，根据 nums[r]nums[r] 与 curcur 的关系进行分情况讨论：
 * nums[r] = cur: 此时位置 k 共有 r 种选择，而后面的每个位置由于nums[i] 可以使用多次，每个位置都有 m 种选择，共有 n - p个位置，因此该分支往后共有 r * m^ n−p
 *   种合法方案。且由于 nums[r] = cur，往后还有分支可决策（需要统计），因此需要继续处理；
 * nums[r] <cur：此时算上 nums[r]，位置 k 共有 r + 1 种选择，而后面的每个位置由于 nums[i] 可以使用多次，每个位置都有 m 种选择，共有 n - p 个位置，因此该分支共有 (r + 1) * m^{n - p}
 *   种合法方案，由于 nums[r] < cur，往后的方案数（均满足小于关系）已经在这次被统计完成，累加后进行 break；
 * nums[r] > cur：该分支往后不再满足「大小限制」要求，合法方案数为 0，直接 break。
 * 其他细节：实际上，我们可以将 res1 和 res2 两种情况进行合并处理。
 */
//时间复杂度：由于 digits 最多存在 9 个元素，因此二分的复杂度可以忽略，整体复杂度为 O(logn)
//空间复杂度：O(C)
public class 最大为N的数字组合 {
    int[] nums;
    int dp(int x) {
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }
        int n = list.size(), m = nums.length, ans = 0;
        // 位数和 x 相同
        for (int i = n - 1, p = 1; i >= 0; i--, p++) {
            int cur = list.get(i);
            int l = 0, r = m - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (nums[mid] <= cur) l = mid;
                else r = mid - 1;
            }
            if (nums[r] > cur) {
                break;
            } else if (nums[r] == cur) {
                ans += r * (int) Math.pow(m, (n - p));
                if (i == 0) ans++;
            } else if (nums[r] < cur) {
                ans += (r + 1) * (int) Math.pow(m, (n - p));
                break;
            }
        }
        // 位数比 x 少的
        for (int i = 1, last = 1; i < n; i++) {
            int cur = last * m;
            ans += cur; last = cur;
        }
        return ans;
    }
    public int atMostNGivenDigitSet(String[] digits, int max) {
        int n = digits.length;
        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(digits[i]);
        return dp(max);
    }
}
