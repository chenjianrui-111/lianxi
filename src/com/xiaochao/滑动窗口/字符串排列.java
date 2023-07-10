package com.xiaochao.滑动窗口;

import java.util.HashMap;

/**
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class 字符串排列 {
    public boolean checkInclusion(String p, String s) {
        // ========= 模板：定义相关数据结构，初始化工作============
        HashMap<Character, Integer> window_map = new HashMap<>();
        HashMap<Character, Integer> p_map = new HashMap<>();
        for (int i = 0; i < p.length(); i++){
            char c1 = p.charAt(i);
            p_map.put(c1, p_map.getOrDefault(c1, 0) + 1);
        }
        int left, right, count;
        left = right = count = 0;

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
                if (count == p_map.size()){
                    return true;
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
        return false;
    }
}
