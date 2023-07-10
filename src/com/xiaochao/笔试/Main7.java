package com.xiaochao.笔试;

import java.util.Scanner;

public class Main7 {
    public static String longestCommonPrefix(String[] strs){
        if(strs.length == 0) return "";
        int row = strs.length;
        int col = strs[0].length();
        for(int i = 0; i < col;i++){
            char firstChar = strs[0].charAt(i);
            for(int j = 1;j < row;j++){
                if(strs[j].length() == i || strs[j].charAt(i) != firstChar){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        String b[]=sc.nextLine().split(",");
        String s = longestCommonPrefix(b);
        if (s.length() == 0){
            System.out.println("null");
        }else {
            System.out.println(s);
        }
    }
}
