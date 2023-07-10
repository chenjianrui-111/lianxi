package com.cjr.shuzu;

import java.util.HashMap;

public class wuChongFuZiFuDeZuiChangZiChuan {
    public int lengthOfLongestSubstring(String s) {
        //边界条件判断
        if (s.length()==0) return 0;
        HashMap<Character,Integer> map=new HashMap<>();
        int max=0;
        for (int i = 0,j=0;i<s.length(); i++) {
            //判断是否有重复的元素，如果有重复的元素，就更新j的值
            if (map.containsKey(s.charAt(i))){
                j=Math.max(j,map.get(s.charAt(i))+1);
            }
            //把i指向的值存储到map中
            map.put(s.charAt(i), i);
            //更新j到i之间的最大距离，也就是无重复字符的最长子串
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
