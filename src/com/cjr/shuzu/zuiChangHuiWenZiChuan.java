package com.cjr.shuzu;

public class zuiChangHuiWenZiChuan {
    public String longestPalindrome(String s) {
        int len=s.length();
        if (len<2){
            return  s;
        }
        int maxLen=1;
        int begin=0;
        boolean [][]dp=new boolean[len][len];
        for (int right=1;right<len;right++){
            for (int left=0;left<right;left++){
                if (s.charAt(left) !=s.charAt(right))
                    continue;

                //下面是s.charAt(left)和s.charAt(right)两个
                //字符相同情况下的判断
                //如果只有一个字符，肯定是回文子串
                if (right==left){
                    dp[left][right]=true;
                }
                else if (right-left<=2){
                    dp[left][right]=true;
                }else {
                    dp[left][right]=dp[left+1][right-1];
                }
                //如果字符串从left到right是回文子串，只需要保存最长的即可
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    begin = left;
                }
            }
        }
        //截取最长回文字符串
        return s.substring(begin,begin+maxLen);
    }
}
