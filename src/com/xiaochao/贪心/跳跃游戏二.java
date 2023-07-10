package com.xiaochao.贪心;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class 跳跃游戏二 {
    public int jump(int[] nums) {
        int n = nums.length;
        int ans = 0;
        boolean[] vis = new boolean[n];
        Deque<Integer> d = new ArrayDeque<>();
        vis[0] = true;
        d.addLast(0);
        while (!d.isEmpty()){
            int size = d.size();
            while (size -- > 0){
                int idx = d.pollFirst();
                if (idx == n - 1) return ans;
                for (int i = idx + 1; i <= idx + nums[idx] && i < n; i++) {
                    if (!vis[i]){
                        vis[i] = true;
                        d.addLast(i);
                    }
                }
            }
            ans++;
        }
        return ans;
    }
}
//• 时间复杂度：如果每个点跳跃的距离足够长的话，每次都会将当前点「后面的所有
//点」进行循环入队操作（由于 st 的存在，不一定都能入队，但是每个点都需要被循
//环一下）。复杂度为 O(n2) • 空间复杂度：队列中最多有 n − 1 个元素。复杂度为 O(n)
/**
 *双指针 + 贪心 + 动态规划 本题数据范围只有 103，所以 O(n2) 勉强能过。
 * 如果面试官要将数据范围出到 106，又该如何求解呢？
 * 我们需要考虑 O(n) 的做法。
 * 其实通过 106 这个数据范围，就已经可以大概猜到是道 DP 题。 我们定义 f[i] 为到达第 i 个位置所需要的最少步数，那么答案是 f[n − 1]。 学习过 路径 DP 专题 的同学应该知道，通常确定 DP 的「状态定义」有两种方法。
 * • 一种是根据经验猜一个状态定义，会结合题目给定的维度，和要求的答案去猜。
 * • 另外一种则是通过设计一个合理的 DFS 方法签名来确定状态定义。
 * 这里我是采用第一种方法。
 * 至于如何确定「状态定义」是否可靠，关键是看使用这个状态定义能否推导出合理的「状态转移
 * 方程」，来覆盖我们所有的状态。
 * 不失一般性的考虑 f[n − 1] 该如何转移：
 * 我们知道最后一个点前面可能会有很多个点能够一步到达最后一个点。
 **/
class Solution2{
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j + nums[j] < i) j++;
            f[i] = f[j] + 1;
        }
        return f[n - 1];
    }
}
//• 时间复杂度：O(n) • 空间复杂度：O(n)
