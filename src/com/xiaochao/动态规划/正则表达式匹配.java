package com.xiaochao.动态规划;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 比如说模式串 ".a*b" 就可以匹配文本 "zaaab"，也可以匹配 "cb"；模式串 "a..b" 可以匹配文本 "amnb"；
 * 而模式串 ".*" 就比较牛逼了，它可以匹配任何文本。
 */
public class 正则表达式匹配 {
    public boolean isMatch(String s, String p) {

        boolean table[][] = new boolean[s.length() + 1][p.length() + 1];

        table[0][0] = true;

        for (int col=1; col<table[0].length; col++) {
            char ch = p.charAt(col-1);
            if (col > 1) {
                if (ch == '*') {
                    table[0][col] = table[0][col-2];
                } else {
                    table[0][col] = false;
                }
            } else {
                if (ch == '*') {
                    table[0][col] = true;
                }
            }
        }

        for (int row=1; row<table.length; row++) {
            char ch1 = s.charAt(row-1);
            for (int col=1; col<table[row].length; col++) {
                char ch2 = p.charAt(col-1);
                if (ch1==ch2 || ch2 == '.') {
                    table[row][col] = table[row-1][col-1];
                } else if (ch2 == '*') {
                    if(col > 1) {
                        if (table[row][col-2]) {
                            table[row][col] = true; // * 前面的字符出现0次
                        } else {
                            char prev = p.charAt(col-2);
                            if (prev== ch1 || prev == '.') {
                                table[row][col] = table[row - 1][col]; // * 前面的字符出现多次
                            }
                        }

                    }
                }
            }
        }
        return table[s.length()][p.length()];
    }
}
