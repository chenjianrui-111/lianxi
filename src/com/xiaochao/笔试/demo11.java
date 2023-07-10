package com.xiaochao.笔试;

import java.util.*;

//给定一个字符串找出其中不含有重复字符的最长字串长度
public class demo11 {
    public static void main(String[] args) {
        String s = "aabccdc";
        int x = getBuStr(s);
        System.out.println(x);

    }
    public static int getBuStr(String s){
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            maxLen = Math.max(maxLen,i - left + 1);
        }
        return maxLen;
    }
}


