package com.leixing.滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。
 * 不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * 这是一道使用双指针实现滑动窗口的裸题。
 * 具体的，我们可以先创建一个大小为 26 的数组 c2来统计字符串 p 的词频，另外一个同等大小的数组 c1 用来统计「滑动窗口」内的 s 的子串词频。
 * 当两个数组所统计词频相等，说明找到了一个异位组，将窗口的左端点加入答案。
 */
public class 找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        int[] c1 = new int[26], c2 = new int[26];
        for (int i = 0; i < m; i++) c2[p.charAt(i) - 'a']++;
        for (int l = 0, r = 0; r < n; r++) {
            c1[s.charAt(r) - 'a']++;
            if (r - l + 1 > m) c1[s.charAt(l++) - 'a']--;
            if (check(c1, c2)) ans.add(l);
        }
        return ans;
    }
    boolean check(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }

}
