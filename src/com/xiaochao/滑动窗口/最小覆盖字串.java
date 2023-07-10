package com.xiaochao.滑动窗口;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 最小覆盖字串 {
    public String minWindow(String s, String t) {
        // 记录t 以及 滑动窗口window中 字符与个数的映射关系
        HashMap<Character, Integer> window_map = new HashMap<>();
        HashMap<Character, Integer> t_map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c1 = t.charAt(i);
            t_map.put(c1, t_map.getOrDefault(c1, 0) + 1);
        }
        // 双指针,
        int left = 0;
        int right = 0;
        int count = 0;
        // 用于更新满足的窗口window的长度,如果是len一直是MAX_VALUE，说明没有满足的串
        int len = Integer.MAX_VALUE;
        // 用于记录window串的起始位置，则返回 s[start, len]
        int start = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 只要c是t中出现的字符就更新
            if (t_map.containsKey(c)) {
                window_map.put(c, window_map.getOrDefault(c, 0) + 1);
                // 更新c字符出现的次数
                if (window_map.get(c).equals(t_map.get(c))) {
                    count++;
                }
            }
            // System.out.println(window_map);
            // ----------------------------------
            // 收缩window的长度
            while (count == t_map.size()) {
                // 更新并记录window的长度,以及window的起始位置start
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                left++;

                if (t_map.containsKey(d)) {
                    if (window_map.get(d).equals(t_map.get(d))) {
                        count--;
                    }
                    window_map.put(d, window_map.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}

class Solution {
    public String minWindow(String s, String t) {
        int minStart = -1;
        int minLength = Integer.MAX_VALUE;
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        int m = s.length(), n = t.length();
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) - 1);
        }
        int start = 0, end = 0;
        while (end < m) {
            char curr = s.charAt(end);
            if (counts.containsKey(curr)) {
                counts.put(curr, counts.getOrDefault(curr, 0) + 1);
            }
            while (allIncluded(counts)) {
                if (end - start + 1 < minLength) {
                    minStart = start;
                    minLength = end - start + 1;
                }
                char prev = s.charAt(start);
                if (counts.containsKey(prev)) {
                    counts.put(prev, counts.get(prev) - 1);
                }
                start++;
            }
            end++;
        }
        return minStart < 0 ? "" : s.substring(minStart, minStart + minLength);
    }

    public boolean allIncluded(Map<Character, Integer> counts) {
        Set<Map.Entry<Character, Integer>> entries = counts.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            if (entry.getValue() < 0) {
                return false;
            }
        }
        return true;
    }
}
