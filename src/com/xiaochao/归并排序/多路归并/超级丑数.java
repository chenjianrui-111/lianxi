package com.xiaochao.归并排序.多路归并;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32]
 * 提示：
 * • 1 <= n <= 10^6
 * • 1 <= primes.length <= 100
 * • 2 <= primes[i] <= 1000
 * • 题目数据 保证 primes[i] 是一个质数
 * • primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 */
public class 超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        set.add(1L);
        pq.add(1L);
        while (n-- > 0){
            long x = pq.poll();
            if (n == 0) return (int)x;
            for (int k : primes) {
                if (!set.contains(k * x)){
                    set.add(k * x);
                    pq.add(k * x);
                }
            }
        }
        return -1;
    }
    //• 时间复杂度：令 primes 长度为 m，需要从优先队列（堆）中弹出 n 个元素，每
    //次弹出最多需要放入 m 个元素，堆中最多有 n ∗ m 个元素。复杂度为 O(n ∗mlog (n ∗ m))
    //• 空间复杂度：O(n ∗ m)

    /**
     * 我们令这些有序序列为 arr，最终的丑数序列为 ans。
     * 如果 primes 的长度为 m 的话，我们可以使用 m 个指针来指向这 m 个有序序列 arr 的当前下标。
     * 显然，我们需要每次取 m 个指针中值最小的一个，然后让指针后移（即将当前序列的下一个值放入堆中），不断重复这个过程，直到我们找到第 n 个丑数。
     * 当然，实现上，我们并不需要构造出这 m 个有序序列。
     * 我们可以构造一个存储三元组的小根堆，三元组信息为 (val,i,idx)：
     * • val ：为当前列表指针指向具体值；
     * • i ：代表这是由 primes[i] 构造出来的有序序列；
     * • idx：代表丑数下标，存在关系 val = ans[idx] ∗ primes[i]。
     * 起始时，我们将所有的 (primes[i],i, 0) 加入优先队列（堆）中，每次从堆中取出最小元素，
     * 那么下一个该放入的元素为 (ans[idx + 1] ∗ primes[i],i,idx + 1)。
     * 另外，由于我们每个 arr 的指针移动和 ans 的构造，都是单调递增，因此我们可以通过与当前
     * 最后一位构造的 ans[x] 进行比较来实现去重，而无须引用常数较大的 Set 结构。
     */
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int m = primes.length;
        PriorityQueue<int[]> pq =new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int i = 0; i < m ; i++) {
            pq.add(new int[]{primes[i],i,0});
        }
        int[] ans = new int[n];
        ans[0] = 1;
        for (int j = 1; j < n; ) {
            int[] poll = pq.poll();
            int val = poll[0], i = poll[1], idx = poll[2];
            if (val != ans[j - 1]) ans[j++] = val;
            pq.add(new int[]{ans[idx + 1] * primes[i], i, idx + 1});
        }
        return ans[n - 1];
    }
    //• 时间复杂度：需要构造长度为 n 的答案，每次构造需要往堆中取出和放入元素，堆
    //中有 m 个元素，起始时，需要对 primes 进行遍历，复杂度为 O(m)。整体复杂
    //度为 O(max(m, nlogm))
    //• 空间复杂度：存储 n 个答案，堆中有 m 个元素，复杂度为 O(n + m)
}
