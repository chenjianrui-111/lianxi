package com.leixing.双指针;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，该数组由 互不相同 的数字组成。另给你两个整数 start 和 goal 。
 * 整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。你可以对数字 x 重复执行下述运算：
 *
 * 如果 0 <= x <= 1000 ，那么，对于数组中的任一下标 i（0 <= i < nums.length），可以将 x 设为下述任一值：
 *
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i]（按位异或 XOR）
 * 注意，你可以按任意顺序使用每个 nums[i] 任意次。使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，但该运算执行后将不能执行其他运算。
 *
 * 返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1 。 
 * 示例 1：
 * 输入：nums = [1,3], start = 6, goal = 4
 * 输出：2
 * 解释：
 * 可以按 6 → 7 → 4 的转化路径进行，只需执行下述 2 次运算：
 * - 6 ^ 1 = 7
 * - 7 ^ 3 = 4
 * 示例 2：
 * 输入：nums = [2,4,12], start = 2, goal = 12
 * 输出：2
 * 解释：
 * 可以按 2 → 14 → 12 的转化路径进行，只需执行下述 2 次运算：
 * - 2 + 12 = 14
 * - 14 - 2 = 12
 * 示例 3：
 * 输入：nums = [3,5,7], start = 0, goal = -4
 * 输出：2
 * 解释：
 * 可以按 0 → 3 → -4 的转化路径进行，只需执行下述 2 次运算：
 * - 0 + 3 = 3
 * - 3 - 7 = -4
 * 注意，最后一步运算使 x 超过范围 0 <= x <= 1000 ，但该运算仍然可以生效。
 * 示例 4：
 * 输入：nums = [2,8,16], start = 0, goal = 1
 * 输出：-1
 * 解释：
 * 无法将 0 转化为 1
 * 示例 5：
 * 输入：nums = [1], start = 0, goal = 3
 * 输出：3
 * 解释：
 * 可以按 0 → 1 → 2 → 3 的转化路径进行，只需执行下述 3 次运算：
 * - 0 + 1 = 1
 * - 1 + 1 = 2
 * - 2 + 1 = 3
 * 提示：
 * 1 <= nums.length <= 1000
 * -109 <= nums[i], goal <= 109
 * 0 <= start <= 1000
 * start != goal
 * nums 中的所有整数互不相同
 */
public class 转化数字的最小运算数 {

    public int minimumOperations(int[] nums, int start, int goal) {

        Deque<Integer> deque=new ArrayDeque<>();
        Map<Integer,Integer> map=new HashMap<>();
        deque.addLast(start);
        map.put(start,0);
        while (!deque.isEmpty()){
            int cur=deque.pollFirst();
            int step=map.get(cur);
            for (int i: nums){
                int [] result=new int []{cur+i,cur-i,cur^i};
                for (int next: result){
                    if (next == goal) return step + 1;
                    if (next < 0 || next >1000) continue;
                    if (map.containsKey(next)) continue;
                    map.put(next,step + 1);
                    deque.addLast(next);
                }
            }
        }
        return -1;
    }
}
/**
 *双向 BFS
 * 这还是一道很好的双向 BFS 模板题。
 * 之前我没有找到这样的模板题，不得已使用了 LeetCode 难度标记为「困难」的 127. 单词接龙 来作为双向 BFS 的入门题。
 * PS. 事实上，那道题也不难，如果你还没做过 127. 单词接龙，在学习完本题解后，可以尝试做一下。
 * 回到本题，我们可以同时从两个方向进行搜索：
 * 正向搜索：使用队列 d1 实现从 start 到 goal 的通路搜索，为满足「0 <= x <= 1000」的条件限制，我们需要进行「出队检查」，只有满足「0 <= x <= 1000」的出队元素，才进行下一步的拓展；
 * 反向搜索：使用队列 d2 实现从 goal 到 start 的通路搜索，为满足「0 <= x <= 1000」的条件限制，我们需要进行「入队检查」，只有下一个拓展元素 next 满足「0 <= x <= 1000」才能进行入队。
 * 同时，我们使用两个「哈希表」分别记录两个搜索方向中出现过的结果。
 * 一旦在某条搜索通路中搜到了另一条搜索通路中出现过的结果，说明找到了一条合法的搜索通路，返回该通路长度。
 */
class Solution {
    int[] nums;
    public int minimumOperations(int[] _nums, int s, int t) {
        nums = _nums;
        Deque<Long> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<Long, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.addLast(s * 1L);
        d2.addLast(t * 1L);
        m1.put(s * 1L, 0);
        m2.put(t * 1L, 0);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() < d2.size()) {
                int ans = update(d1, m1, d2, m2, true);
                if (ans != -1) return ans;
            } else {
                int ans = update(d2, m2, d1, m1, false);
                if (ans != -1) return ans;
            }
        }
        return -1;
    }
    int update(Deque<Long> d1, Map<Long, Integer> m1, Deque<Long> d2, Map<Long, Integer> m2, boolean flag) {
        int m = d1.size();
        while (m-- > 0) {
            long cur = d1.pollFirst();
            int step = m1.get(cur);
            for (int i : nums) {
                if (flag) {
                    // 正向搜索：进行出队检查，只有出队元素符合条件，才能使用出队元素往下拓展
                    if (0 <= cur && cur <= 1000) {
                        long[] result = new long[]{cur + i, cur - i, cur ^ i};
                        for (long next : result) {
                            if (m2.containsKey(next)) return step + 1 + m2.get(next);
                            if (!m1.containsKey(next)) {
                                d1.addLast(next);
                                m1.put(next, step + 1);
                            }
                        }
                    }
                } else {
                    // 反向搜索：进行入队检查，只有拓展元素符合条件，才能将拓展元素入队
                    long[] result = new long[]{cur + i, cur - i, cur ^ i};
                    for (long next : result) {
                        if (0 <= next && next <= 1000) {
                            if (m2.containsKey(next)) return step + 1 + m2.get(next);
                            if (!m1.containsKey(next)) {
                                d1.addLast(next);
                                m1.put(next, step + 1);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
