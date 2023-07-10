package com.xiaochao.前缀和;

/**
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 * 并返回一个包含给定查询 queries 所有结果的数组。
 * 示例 1：
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * 示例 2：
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 * 提示：
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 */

/**
 * 基本分析
 * 令数组 arr 和数组 queries 的长度分别为 n 和 m 。 n 和 m 的数据范围均为 104，因此 O(m ∗ n) 的暴力做法我们不用考虑了。
 * 数据范围要求我们做到「对数复杂度」或「线性复杂度」。
 * 本题主要利用异或运算中的「相同数值进行运算结果为 0」的特性。
 * 对于特定数组 [a1, a2, a3, ..., an]，要求得任意区间 [l, r] 的异或结果，可以通过 [1, r] 和 [1, l − 1] 的异或结果得出：
 * xor(l,r) = xor(l,r) + xor(1,l-1)
 * 本质上还是利用集合（区间结果）的容斥原理。只不过前缀和需要利用「减法（逆运算）」做容
 * 斥，而前缀异或是利用「相同数值进行异或结果为 0（偶数次的异或结果为 0）」的特性实现容斥。
 * 对于「区间求值」问题，之前在 【题解】307. 区域和检索 - 数组可修改 也做过总结。 针对不同的题目，有不同的方案可以选择（假设有一个数组）：
 * 1. 数组不变，求区间和：「前缀和」、「树状数组」、「线段树」
 * 2. 多次修改某个数，求区间和：「树状数组」、「线段树」
 * 3. 多次整体修改某个区间，求区间和：「线段树」、「树状数组」（看修改区间的数据范围）
 * 4. 多次将某个区间变成同一个数，求区间和：「线段树」、「树状数组」（看修改区间的数据范围）
 * 虽然「线段树」能解决的问题最多，但「线段树」代码很长，且常数很大，实际表现不算好。我
 * 们只有在不得不用的情况下才考虑「线段树」。
 * 本题我们使用「树状数组」和「前缀和」来求解。
 */
/**
 *前缀异或
 * 「树状数组」的查询复杂度为 O(log n)，而本题其实不涉及「修改操作」，我们可以使用「前缀异或」来代替「树状数组」。
 * 虽说「树状数组」也有 O(n) 的创建方式，但这里使用「前缀异或」主要是为了降低查询的复杂度。
 */
public class 子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] qs) {
        int n = arr.length, m = qs.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] ^ arr[i - 1];
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = qs[i][0] + 1, r = qs[i][1] + 1;
            ans[i] = sum[r] ^ sum[l - 1];
        }
        return ans;
    }
}
//• 时间复杂度：令 arr 数组长度为 n ， qs 数组的长度为 m 。预处理前缀和数组 复杂度为 O(n)；
// 查询的复杂度为 O(m)。整体复杂度为 O(n + m) • 空间复杂度：O(n)
/**
 *树状数组
 * 使用「树状数组」分段记录我们某些区间的「异或结果」，再根据 queries 中的询问将分段
 * xor(l, r) = xor(1, r) ⊕ xor(1, l − 1)
 * 「异或结果」汇总（执行异或运算），得出最终答案
 */
class Solution16{
    int n;
    int[] c = new int[100009];
    int lowbit(int x){
        return x & -x;
    }
    void add(int x,int u){
        for (int i = x; i <= n ; i += lowbit(i)) {
            c[i] ^= u;
        }
    }
    int query(int x){
        int ans = 0;
        for (int i = x; i > 0 ; i -= lowbit(i)) {
            ans ^= c[i];
        }
        return ans;
    }
    public int[] xorQueries(int[] arr,int [][] qs){
        n = arr.length;
        int m = qs.length;
        for (int i = 1; i <= n ; i++) {
            add(i,arr[i - 1]);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = qs[i][0] + 1, r = qs[i][1] + 1;
            ans[i] = query(r) ^ query(l - 1);
        }
        return ans;
    }
}
//• 时间复杂度：令 arr 数组长度为 n ， qs 数组的长度为 m 。创建树状数组复杂 度为 O(n log n)；
// 查询的复杂度为 O(m log n)。整体复杂度为 O((n + m)log n) • 空间复杂度：O(n)



