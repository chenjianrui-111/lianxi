package com.xiaochao.数组链表.滑动窗口;

import java.util.HashMap;

/**
 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 注意：
 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 示例 1：
 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"
 */
public class 最小覆盖字串 {
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> need =new HashMap<>();
        HashMap<Character,Integer> window =new HashMap<>();
        char[] t1 =t.toCharArray();
        char[] s1=s.toCharArray();
        for (char c : t1) {
            need.put(c,need.getOrDefault(c,0) + 1);
        }
        int left = 0 ,right = 0;
        //valid 变量表示窗口中满足 need 条件的字符个数
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len =Integer.MAX_VALUE;
        while (right < s.length()){
            char c = s1[right];
            right++;
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == need.size()){
                // 在这里更新最小覆盖子串
                if (right - left < len){
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s1[left];
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                        window.put(d,window.getOrDefault(d,0) - 1);
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start+len);
    }
}
