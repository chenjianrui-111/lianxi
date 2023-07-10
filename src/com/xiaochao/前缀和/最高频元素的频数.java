package com.xiaochao.前缀和;

import java.util.*;

/**
 * Tag : 「枚举」、「哈希表」、「排序」、「前缀和」、「二分」、「滑动窗口」、「双指针」
 * 元素的频数是该元素在一个数组中出现的次数。
 * 给你一个整数数组 nums 和一个整数 k 。
 * 在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * 执行最多 k 次操作后，返回数组中最高频元素的最大可能频数。
 * 示例 1：
 * 输入：nums = [1,2,4], k = 5
 * 输出：3 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 * 提示：
 * • 1 <= nums.length <= 105 • 1 <= nums[i] <= 105 • 1 <= k <= 10
 */

/**
 * 枚举
 * 一个朴素的做法是，先对原数组 nums 进行排序，然后枚举最终「频数对应值」是哪个。
 * 利用每次操作只能对数进行加一，我们可以从「频数对应值」开始往回检查，从而得出在操作次
 * 数不超过 k 的前提下，以某个值作为「频数对应值」最多能够凑成多少个。
 * 算法整体复杂度为 O(n2)
 */
public class 最高频元素的频数 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> map =new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int ans = 1;
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i), cnt = map.get(x);
            if (i > 0){
                int p = k;
                for (int j = i - 1; j >=  0 ; j++) {
                    int y = list.get(j);
                    int diff = x - y;
                    if (p >= diff){
                        int add = p / diff;
                        int min = Math.min(map.get(y),add);
                        p -= min * diff;
                        cnt += min;
                    }else {
                        break;
                    }
                }
            }
            ans = Math.max(ans,cnt);
        }
        return ans;
    }
}
///时间复杂度：得到去重后的频数后选集合复杂度为 O(n)；最坏情况下去重后仍有 n 个频数，且判断 k 次操作内某个频数最多凑成多少复杂度为 O(n)。整体复杂度为 O(n^2)
//空间复杂度：O(n)
/**
 *排序 + 前缀和 + 二分 + 滑动窗口
 * 先对原数组 nums 进行从小到大排序，如果存在真实最优解 len，意味着至少存在一个大小为
 * len 的区间 [l, r]，使得在操作次数不超过 k 的前提下，区间 [l, r] 的任意值 nums[i] 的值调 整为 nums[r]。
 * 这引导我们利用「数组有序」&「前缀和」快速判断「某个区间 [l, r] 是否可以在 k 次操作内将所有值变为 nums[r]」：
 * 具体的，我们可以二分答案 len 作为窗口长度，利用前缀和我们可以在 O(1) 复杂度内计算任
 * 意区间的和，同时由于每次操作只能对数进行加一，即窗口内的所有数最终变为 nums[r] ，最
 * 终目标区间和为 nums[r] ∗ len，通过比较目标区间和和真实区间和的差值，我们可以知道 k次操作是否能将当前区间变为 nums[r]。
 * 上述判断某个值 len 是否可行的 check 操作复杂度为 O(n)，因此算法复杂度为 O(n log n)
 */
class Solution19{
    int[] nums,sum;
    int n,k;
    public int maxFrequency(int[] _nums, int _k) {
        nums = _nums;
        k  =_k;
        n = nums.length;
        Arrays.sort(nums);
        sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int l  = 0,r = n;
        while (l < r){
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }
    boolean check(int len){
        for (int l = 0; l + len -1 < n ; l++) {
            int r = len + l - 1;
            int cur = sum[r + 1] - sum[l];
            int t = nums[r] * len;
            if (t - cur <= k) return true;
        }
        return false;
    }
}
//• 时间复杂度：排序的复杂度为 O(n log n)；计算前缀和数组复杂度为 O(n) ；
// check 函数的复杂度为 O(n)，因此二分复杂度为 O(n log n)。整体复杂度为 O(n log n) • 空间复杂度：O(n)
