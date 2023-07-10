package com.leixing.滑动窗口;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 * 提示：
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 * 滑动窗口 & 二分
 * 根据题意，对于任意一个位置 i（假设其值为 u），我们其实是希望在下标范围为 [max(0, i - k), i)内找到值范围在 [u - t, u + t]的数。
 * 一个朴素的想法是每次遍历到任意位置 i 的时候，往后检查 k 个元素，但这样做的复杂度是 O(nk) 的，会超时。
 * 显然我们需要优化「检查后面 k 个元素」这一过程。
 * 我们希望使用一个「有序集合」去维护长度为 k 的滑动窗口内的数，该数据结构最好支持高效「查询」与「插入/删除」操作：
 * 查询：能够在「有序集合」中应用「二分查找」，快速找到「小于等于 uu 的最大值」和「大于等于 u 的最小值」（即「有序集合」中的最接近 u 的数）。
 * 插入/删除：在往「有序集合」添加或删除元素时，能够在低于线性的复杂度内完成（维持有序特性）。
 * 或许你会想到近似 O(1) 操作的 HashMap，但注意这里我们需要找的是符合  abs(nums[i],nums[j])⩽t 的两个值，nums[i] 与 nums[j] 并不一定相等，而 HashMap 无法很好的支持「范围查询」操作。
 * 我们还会想到「树」结构。
 * 例如 AVL，能够让我们在最坏为 O(logk) 的复杂度内取得到最接近 u 的值是多少，但本题除了「查询」以外，还涉及频繁的「插入/删除」操作（随着我们遍历 nums 的元素，滑动窗口不断右移，我们需要不断的往「有序集合」中删除和添加元素）。
 * 简单采用 AVL 树，会导致每次的插入删除操作都触发 AVL 的平衡调整，一次平衡调整会伴随着若干次的旋转。
 * 而红黑树则很好解决了上述问题：将平衡调整引发的旋转的次数从「若干次」限制到「最多三次」。
 * 因此，当「查询」动作和「插入/删除」动作频率相当时，更好的选择是使用「红黑树」。
 * 也就是对应到 Java 中的 TreeSet 数据结构（基于红黑树，查找和插入都具有折半的效率）。
 * 其他细节：由于 nums 中的数较大，会存在 int 溢出问题，我们需要使用 long 来存储。
 */
public class 存在重复元素3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n=nums.length;
        TreeSet<Long> ts=new TreeSet<>();
        for (int i=0;i<n;i++){
            Long u=nums[i] *1L;
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t) return true;
            if(r != null && r - u <= t) return true;
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k) ts.remove(nums[i - k] * 1L);
        }
        return false;
    }
}
/**
 *时间复杂度：TreeSet 基于红黑树，查找和插入都是 O(logk) 复杂度。
 * 整体复杂度为 O(nlogk)
 * 空间复杂度：O(k)
 */
