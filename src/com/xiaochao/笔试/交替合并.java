package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 交替合并 {
    public static String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        char [] ca = new char[len1+len2];
        int p = 0;
        int p1 = 0;
        while(p < ca.length) {
            if(len1 < len2 && p1 >= len1) {
                ca[p] = word2.charAt(p1);
            }
            else if(len1 > len2 && p1 >= len2) {
                ca[p] = word1.charAt(p1);
            } else{

                ca[p] = word1.charAt(p1);
                ca[++p] =  word2.charAt(p1);

            }
            p++;
            p1++;
        }

        return new String(ca);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(",");
        String word1 = split[0];
        String word2 = split[1];
        String s = mergeAlternately(word1, word2);
        System.out.println(s);
    }
}
