package com.xiaochao.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class 最长无重复子串 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map=new HashMap<>();
        int ans=0;
        for (int start=0,end =0 ;end<s.length();end++){
            char right=s.charAt(end);
            map.put(right,map.getOrDefault(right,0)+1);
            while (map.get(right) > 1){
                char left=s.charAt(start);
                map.put(left,map.get(left) - 1);
                start++;
            }
            ans=Math.max(ans,end-start+1);
        }
        return ans;
    }
}
