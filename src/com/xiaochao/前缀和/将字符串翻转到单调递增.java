package com.xiaochao.前缀和;

/**
 * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 * 返回使 s 单调递增的最小翻转次数。
 * 示例 1：
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 * 示例 2：
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 * 示例 3：
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000。
 */

import java.util.Arrays;

/**
 * LIS 问题贪心解
 * 根据题意，不难想到将原题进行等价转换：令 s 长度为 ，原问题等价于在 s 中找到最长不下降子序列，设其长度为 ans ，那么对应的 n - ans 即是答案。
 * 由于数据范围为 1e5 ，因此我们需要使用 LIS 问题的贪心求解方式：使用 g 数组记录每个长度的最小结尾元素，即 g[len] = x 含义为长度为 len 的最长不下降子序列的结尾元素为 x ，
 * 然后在从前往后处理每个 t=s[i] 时，由于是求解「最长不下降子序列」，等价于找「满足大于  的最小下标」，这可以运用「二分」进行求解。
 * 不了解 LIS 问题或者不清楚 LIS 问题贪心解法的同学可以看前置 🧀 : LCS 问题与 LIS 问题的相互关系，以及 LIS 问题的最优解证明，里面详细讲解了 LIS 贪心解的正确性证明，以及 LCS 和 LIS 在特定条件下存在的内在联系。
 */
public class 将字符串翻转到单调递增 {
    public int minFlipsMonoIncr(String s) {
        char[] cs = s.toCharArray();
        int n =cs.length,ans = 0;
        int[] g= new int[n + 10];
        Arrays.fill(g,n + 10);
        for (int i = 0; i < n ; i++) {
            int t = s.charAt(i) - '0';
            int l = 1,r= i + 1;
            while (l < r){
                int mid = l + r >> 1;
                if (g[mid] > t) r= mid;
                else l = mid + 1;
            }
            g[r] = t;
            ans = Math.max(ans,r);
        }
        return n - ans;
    }
}
//时间复杂度：O(nlogn)
//空间复杂度：O(n)
/**
 *前缀和 + 枚举
 * 更进一步，利用 s 只存在 0 和 1 两种数值，我们知道最后的目标序列形如 000...000、000...111 或 111...111 的形式。
 * 因此我们可以枚举目标序列的 1 和 1 分割点位置 idx（分割点是 0 是 1 都可以，不消耗改变次数）。
 * 于是问题转换为：分割点 idx 左边有多少个 1（目标序列中分割点左边均为 0，因此 1 的个数为左边的改变次数），
 * 分割点 idx 的右边有多少个 0（目标序列中分割点右边均为 ，因此  的个数为右边的改变次数），两者之和即是分割点为 idx 时的总变化次数，
 * 所有 idx  的总变化次数最小值即是答案。
 * 而求解某个点左边或者右边有多少 1 和 0 可通过「前缀和」进行优化。
 */
class Solution4 {
    public int minFlipsMonoIncr(String s) {
        char[] cs= s.toCharArray();
        int n =cs.length, ans = n;
        int[] sum = new int[n + 10];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (cs[i - 1] -'0');
        }
        for (int i = 1; i <= n ; i++) {
            int l = sum[i - 1],r = (n - i) - (sum[n] - sum[i]);
            ans = Math.min(ans,l+r);
        }
        return ans;
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
