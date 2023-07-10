package com.xiaochao.字符串;

public class 最长无重复字符 {
    public static int lnrs1(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char [] str =s.toCharArray();
        int N = str.length;
        int[] last =new int[26];
        for (int i = 0; i < 26 ; i++) {
            last[i] = -1;
        }
        //当前字符上次出现的位置
        last[str[0] - 'a'] = 0;
        int max = 1;
        //记录 i - 1 位置的 dp 值
        int preMaxLen = 1;
        for (int i = 1; i < N ; i++) {
            preMaxLen = Math.min(i - last[str[i] - 'a'],preMaxLen + 1);
            max = Math.max(max,preMaxLen);
            last[str[i] - 'a'] = i;
        }
        return max;
    }
}
