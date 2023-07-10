package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 小红的字符串大小写变换 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while((line = br.readLine()) != null){
            String[] s= br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            for (int i = 0; i <line.length() ; i++) {
                if (i <= k){
                    sb1.append(line.charAt(i));
                }else {
                    sb2.append(line.charAt(i));
                }
            }
        }
        String s1 = sb1.toString().toUpperCase();
        String s2 =sb2.toString().toLowerCase();
        String temp = s1 + s2;
        for (int i = 0; i <temp.length() ; i++) {
            System.out.print(temp.charAt(i));
        }
    }
}

