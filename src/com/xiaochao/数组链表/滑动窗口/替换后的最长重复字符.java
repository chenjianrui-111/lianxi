package com.xiaochao.数组链表.滑动窗口;

/**
 * 给你一个字符串 s 和一个整数 k。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符，该操作最多可执行 k 次。
 * 请问在执行若干次更改操作后，包含相同字母的最长子字符串的长度是多少。
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个 'A' 替换为两个 'B', 反之亦然。
 */
public class 替换后的最长重复字符 {
    public int characterReplacement(String s, int k) {
        int left =0 ,right = 0;
        // 统计窗口中每个字符的出现次数
        int[] windowCharCount = new int[26];
        // 记录窗口中字符的最多重复次数
        // 记录这个值的意义在于，最划算的替换方法肯定是把其他字符替换成出现次数最多的那个字符
        // 所以窗口大小减去 windowMaxCount 就是所需的替换次数
        int windowMaxCount = 0;
        //记录结果长度
        int res = 0;
        //开始滑动窗口模板
        while (right < s.length()){
            //扩大窗口
            windowCharCount[s.charAt(right) - 'A']++;
            windowMaxCount = Math.max(windowMaxCount,windowCharCount[s.charAt(right) - 'A']);
            right++;
            while (right - left -windowMaxCount > k){
                //窗口减小
                windowCharCount[s.charAt(left) - 'A']--;
                left++;
                // 这里不用更新 windowMaxCount
                // 因为只有 windowMaxCount 变得更大的时候才可能获得更长的重复子串，才会更新 res
            }
            // 此时一定是一个合法的窗口
            res = Math.max(res,right - left);
        }
        return res;
    }
}
