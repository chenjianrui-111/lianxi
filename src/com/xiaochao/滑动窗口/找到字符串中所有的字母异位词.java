package com.xiaochao.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 */
public class 找到字符串中所有的字母异位词 {

    public List<Integer> findAnagrams(String s, String p) {
        // ========= 模板：定义相关数据结构，初始化工作============
        HashMap<Character, Integer> window_map = new HashMap<>();
        HashMap<Character, Integer> p_map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c1 = p.charAt(i);
            p_map.put(c1, p_map.getOrDefault(c1, 0) + 1);
        }
        int left, right, count;
        left = right = count = 0;
        ArrayList<Integer> res = new ArrayList<>();

        // ================== 模板：开始滑动窗口=====================
        while (right < s.length()) {

            // =========== 模板：向右滑动窗口，装填满足的字符到map中==========
            char c = s.charAt(right);
            right++;
            if (p_map.containsKey(c)) {
                window_map.put(c, window_map.getOrDefault(c, 0) + 1);
                if (window_map.get(c).equals(p_map.get(c))) {
                    count++;
                }
            }

            // =========== 根据题意：此时“有可能”出现满足条件的解 ==========
            while (right - left == p.length()) {

                // ============= 根据题意：获取“真正”满足条件的解 =============
                if (count == p_map.size()) {
                    res.add(left);
                }

                // ========== 模板：左指针向右滑动，寻找下一个可行解/优化已知解======
                char d = s.charAt(left);
                left++;
                if (p_map.containsKey(d)) {
                    if (window_map.get(d).equals(p_map.get(d))) {
                        count--;
                    }
                    window_map.put(d, window_map.getOrDefault(d, 0) - 1);
                }
            }
        }
        return res;
    }
}
class Solution2 {
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
