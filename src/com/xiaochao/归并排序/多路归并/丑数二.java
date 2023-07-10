package com.xiaochao.归并排序.多路归并;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * 提示：
 * 1 <= n <= 1690
 */
public class 丑数二 {
    /**
     * 优先队列（小根堆）解法
     * 有了基本的分析思路，一个简单的解法是使用优先队列：
     * 1. 起始先将最小丑数 1 放入队列
     * 2. 每次从队列取出最小值 x，然后将 x 所对应的丑数 2x、3x 和 5x 进行入队。
     * 3. 对步骤 2 循环多次，第 n 次出队的值即是答案。
     * 为了防止同一丑数多次进队，我们需要使用数据结构 Set 来记录入过队列的丑数。
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] nums = new int[]{2,3,5};
        Set<Long> set = new HashSet<>();
        Queue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);
        for (int i = 1; i <= n ; i++) {
            long x = queue.poll();
            if (i == n) return (int)x;
            for (int num : nums) {
                long t = num * x;
                if (!set.contains(t)){
                    set.add(t);
                    queue.add(t);
                }
            }
        }
        return -1;
    }
    //• 时间复杂度：从优先队列中取最小值为 O(1)，往优先队列中添加元素复杂度为
//O(log n)。整体复杂度为 O(n log n)
//• 空间复杂度：O(n)

    /**
     * 多路归并（多指针）解法
     * 举个🌰，假设我们需要求得 [1, 2, 3, ..., 10, 12] 丑数序列 arr 的最后一位，那么该序列可以看
     * 作以下三个有序序列归并而来：
     * • 1 ∗ 2, 2 ∗ 2, 3 ∗ 2, ..., 10 ∗ 2, 12 ∗ 2 ，将 2 提出，即 arr ∗ 2
     * • 1 ∗ 3, 2 ∗ 3, 3 ∗ 3, ..., 10 ∗ 3, 12 ∗ 3 ，将 3 提出，即 arr ∗ 3
     * • 1 ∗ 5, 2 ∗ 5, 3 ∗ 5, ..., 10 ∗ 5, 12 ∗ 5 ，将 5 提出，即 arr ∗ 5
     * 因此我们可以使用三个指针来指向目标序列 arr 的某个下标（下标 0 作为哨兵不使用，起始都
     * 为 1），使用 arr[下标] ∗ 质因数 代表当前使用到三个有序序列中的哪一位，同时使用 idx
     * 表示当前生成到 arr 哪一位丑数。
     */
    public int nthUglyNumber2(int n) {
        //ans用作存储已有丑数(从下标1开始存储，第一个丑数为1)
        int[] ans = new int[n + 1];
        ans[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        for (int i2 = 1, i3 = 1, i5 = 1,idx = 2;idx <= n; idx++) {
            // 由 ans[iX] * X 可得当前有序序列指向哪一位
            int a= ans[i2] * 2,b = ans[i3] * 3,c = ans[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a,Math.min(b,c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            ans[idx] = min;
        }
        return ans[n];
    }
    //• 时间复杂度：O(n)
    //• 空间复杂度：O(n)
}

