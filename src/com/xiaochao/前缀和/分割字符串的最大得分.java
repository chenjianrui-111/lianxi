package com.xiaochao.前缀和;

/**
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 * 示例 1：
 * 输入：s = "011101"
 * 输出：5
 * 解释：
 * 将字符串 s 划分为两个非空子字符串的可行方案有：
 * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
 * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
 * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
 * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
 * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
 * 示例 2：
 * 输入：s = "00111"
 * 输出：5
 * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 * 示例 3：
 * 输入：s = "1111"
 * 输出：3
 * 提示：
 * 2 <= s.length <= 500
 * 字符串 s 仅由字符 '0' 和 '1' 组成。
 */
//前缀和
//构建前缀和数组来记录每个前缀中 1 个个数，复杂度为 O(N) ，枚举每个分割点，搭配前缀和数组计算左串中 0 的数量和右串中 1 的数量，
// 取所有得分的最大值即是答案。
public class 分割字符串的最大得分 {
    public int maxScore(String s) {
        int n = s.length(),ans = 0;
        int[] sum = new int[n+10];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (s.charAt(i - 1) - '0');
        }
        for (int i = 1; i <= n - 1 ; i++) {
            // 0 的数量
            int a = i - sum[i];
            int b = sum[n] - sum[i];
            ans = Math.max(ans,a + b);
        }
        return ans;
    }
}
/**
 *模拟
 * 更进一步，利用 s 中有只有 0 和 1，我们可以遍遍历 s 边计算得分（而无须预处理前缀和数组），起始分割点为 s[0]，
 * 此时得分为 s[0] 中 0 的个数加上 s[1...(n-1)] 中 1 的个数。
 * 然后继续往后处理 s，当 s[i] = 0，说明有一个 0 从右串中移到了左串，并且 0 在右串中不得分，在左串中得分，因此总得分加一；
 * 而当 ，说明有一个 s[i]= 1 从右串中移到了左串，而 1 在右串中得分，在左串中不得分，因此总得分减一。在所有得分中取最大值即是答案
 */
class Solution1{
    public int maxScore(String s) {
        int n = s.length(),cur = s.charAt(0) == '0' ? 1 : 0;
        for (int i = 1; i < n ; i++) {
            cur += s.charAt(i) - '0';
        }
        int ans = cur;
        for (int i = 1; i < n - 1 ; i++) {
            cur += s.charAt(i) == '0' ?  1 : -1;
            ans = Math.max(ans,cur);
        }
        return ans;
    }
}
