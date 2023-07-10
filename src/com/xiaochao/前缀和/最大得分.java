package com.xiaochao.前缀和;

/**
 * 你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。
 * 一条 合法路径 定义如下：
 * 选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
 * 从左到右遍历当前数组。
 * 如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
 * 得分定义为合法路径中不同数字的和。
 * 请你返回所有可能合法路径中的最大得分。
 * 由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
 * 输入：nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * 输出：30
 * 解释：合法路径包括：
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],（从 nums1 开始遍历）
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]  （从 nums2 开始遍历）
 * 最大得分为上图中的绿色路径 [2,4,6,8,10] 。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 前缀和 + 构造（分段计算）
 * 一个简单且正确的做法，是我们构造一种决策方案，使得能够直接计算出最大得分。
 * 首先，在最佳路径中所有的公共点都必然会经过，因此我们可以将值相等的点进行合并，即看作同一个点。
 * 利用两个数组均满足「单调递增」，我们可以通过 o(n+m) 的复杂度统计出那些公共点，以二元组 (i,j) 的形式存储到 list 数组（二元组含义为 nums1[i] = nums2[j]）。
 * 对于 list 中的每对相邻元素（相邻公共点），假设为 (ai,bi) 和(ci,di) ，我们可以通过「前缀和」计算出 nums1[ai...ci]  以及 nums2[bi...di] 的和，从而决策出在 nums1[ai]（或者说是 nums2[bi] ，这两个是同一个点）时，我们应当走哪一段。
 * 当计算完所有公共点之间的得分后，对于最佳路线的首位两端，也是结合「前缀和」做同样的逻辑处理即可。
 */
public class 最大得分 {
    int MOD=(int)1e9 + 7;
    public int maxSum(int[] nums1, int[] nums2) {
        int n  = nums1.length, m = nums2.length;
        long[]s1 = new long[n+10],s2 = new long[m+10];
        for (int i = 1; i <= n ; i++) {
            s1[i] = s1[i-1] + nums1[i-1];
        }
        for (int i = 0; i <= m ; i++) {
            s2[i] = s2[i-1] + nums2[i-1];
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0,j = 0; i < n && j < m ;) {
            if (nums1[i] == nums2[j]) list.add(new int[]{i,j});
            if (nums1[i] < nums2[j]) i++;
            else j++;
        }
        long ans = 0;
        // P1 P2 为当前相同节点的上一个节点坐标
        for (int i = 0, p1 = -1,p2 = -1  ; i <= list.size() ; i++) {
            int idx1 = 0, idx2 = 0;
            if (i < list.size()){
                int[] info = list.get(i);
                idx1 = info[0];idx2 = info[1];
            }else {
                //交点为末尾
                idx1 = n - 1;idx2 = m - 1;
            }
            long t1 = s1[idx1 + 1] - s1[p1 + 1], t2 = s2[idx2 + 1] - s2[p2 + 1];
            ans += Math.max(t1,t2);
            p1 = idx1 ; p2 = idx2;
        }
        return (int) (ans % MOD);
    }
}
//时间复杂度：O(N+M)
//空间复杂度：O(N+M)
/**
 *序列 DP
 * 另外一个较为常见的做法是「序列 DP」做法。
 * 定义 f[i] 代表在 nums1 上进行移动，到达 nums1[i] 的最大得分；定义 g[j]  代表在 nums2 上进行移动，到达 nums[j] 的最大得分。
 * 由于两者的分析是类似的，我们以 f[i] 为例进行分析即可。
 * 不失一般性考虑 f[i] 如何转移，假设当前处理到的是 nums1[i]，根据 nums1[i] 是否为公共点，进行分情况讨论：
 * nums1[i] 不为公共点，此时只能由 nums1[i-1] 转移而来，即有 f[i] = f[i-1] + nums[i] ；
 * nums1[i] 为公共点（假设与 nums2[j] 公共），此时能够从 nums1[i-1] 或 nums2[j-1] 转移而来，我们需要取 f[i-1] 和 g[j-1] 的最大值，即有 f[i] = f[j] = max(f[i-1],g[j-1]) + nums1[i]。
 * 更重要的是，我们需要确保计算 f[i] 时，g[j-1] 已被计算完成。
 * 由于最佳路线必然满足「单调递增」，因此我们可以使用「双指针」来对 f[i] 和 g[j] 同时进行转移，每次取值小的进行更新，从而确保更新过程也是单调的，即当需要计算 f[i] 时，比 nums1[i] 小的 f[X] 和 g[X] 均被转移完成。
 */
class Solution3{
    int MOD = (int)(1e9 + 7);
    public int maxSum(int[] nums1,int[] nums2){
        int n = nums1.length, m = nums2.length;
        long[] f= new long[n + 1], g= new long[m + 1];
        int i = 1,j = 1;
        while (i <= n || j <= m){
            if (i <= n && j <= m){
                if (nums1[i - 1] < nums2[j - 1]){
                    f[i] = f[i - 1] + nums1[i - 1];
                    i++;
                }else if (nums1[i - 1] > nums2[j - 1]){
                    g[j] = g[j - 1] + nums2[j - 1];
                    j++;
                }else {
                    f[i] = g[j] = Math.max(f[i - 1],g[j - 1]) + nums1[i - 1];
                    i++;j++;
                }
            }else if (i <= n){
                f[i] = f[i - 1] + nums1[i - 1];
                i++;
            }else {
                g[j] = g[j - 1] + nums2[j - 1];
                j++;
            }
        }
        return (int) (Math.max(f[n],g[m]) % MOD);
    }
}
//时间复杂度：O(N+M)
//空间复杂度：O(N+M)
