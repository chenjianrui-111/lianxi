package com.cjr.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛牛有两个字符串（可能包含空格）,牛牛想找出其中最长的公共连续子串,希望你能帮助他,并输出其长度。
 * 输入描述:
 * 输入为两行字符串（可能包含空格），长度均小于等于50.
 * 输出描述:
 * 输出为一个整数，表示最长公共连续子串的长度。
 * 输入
 * abcde
 * abgde
 * 输出
 * 2
 */
public class zuiChangGongGongLianXuZiChuan {
    public static void main(String[] args) throws IOException {
        BufferedReader bfd=new BufferedReader(new InputStreamReader(System.in));
        String str1 = bfd.readLine();
        String str2 = bfd.readLine();

        int[][] dp=new int[str1.length()+1][str2.length()+1];
        for (int i=0;i<=str1.length();i++){
            dp[i][0]=0;
        }
        for (int i=0;i<=str2.length();i++){
            dp[0][i]=0;
        }
        int max=Integer.MIN_VALUE;
        for (int i=1;i<=str1.length();i++){
            for (int j=1;j<=str2.length();j++){
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=0;
                }
                max=Math.max(max,dp[i][j]);
            }
        }
        System.out.println(max);
    }
}
