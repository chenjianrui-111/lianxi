package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * 如，输入： Type 输出： epTy
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 如，输入： BabA 输出： aABb
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * 如，输入： By?e 输出： Be?y
 * 数据范围：输入的字符串长度满足
 * 输入描述:
 * 输入字符串
 * 输出描述:
 * 输出字符串
 * 示例1
 * 输入
 * A Famous Saying: Much Ado About Nothing (2012/8).
 * 输出
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class 字符串排序 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line =br.readLine()) !=null){
            char[]ch=line.toCharArray();
            char[] cop=new char[ch.length];
            int offset = 0;
            for (int i = 'A'; i <='Z' ; i++) {
                for (int j = 0; j <ch.length ; j++) {
                    if (ch[j] == i || ch[j]-(char)32 == i){
                        cop[offset++]=ch[j];
                    }
                }
            }
            offset = 0;
            for(int i=0;i<ch.length;i++){
                if((ch[i]>='A' && ch[i]<='Z')||(ch[i]>='a'&&ch[i]<='z')){
                    ch[i] = cop[offset++];
                }
            }
            System.out.println(String.valueOf(ch));
        }
    }
}

