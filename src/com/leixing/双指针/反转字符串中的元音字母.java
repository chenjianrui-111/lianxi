package com.leixing.双指针;

/**
 *给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由 可打印的 ASCII 字符组成
 *
 * 双指针
 * 一个朴素的做法是利用「双指针」进行前后扫描，当左右指针都是元音字母时，进行互换并移到下一位。
 * 由于元音字母相对固定，因此我们可以使用容器将其存储，并使用 static 修饰，确保整个容器的创建和元音字母
 * 的填入在所有测试样例中只会发生一次。
 * 我们期望该容器能在 O(1) 的复杂度内判断是否为元音字母，
 * 可以使用语言自带的哈希类容器（P2 代码）或是使用数组模拟（P1 代码）。
 * 一些细节：由于题目没有说字符串中只包含字母，因此在使用数组模拟哈希表时，
 * 我们需要用当前字符减去 ASCII 码的最小值（空字符），而不是 'A'
 */
public class 反转字符串中的元音字母 {
    static boolean [] hash=new boolean[128];
    static char[] vowels=new char[]{'a','e','i','o','u'};
    static {
        for (char c:vowels){
            hash[c - ' ']=hash[Character.toUpperCase(c) - ' ']=true;
        }
    }
    public String reverseVowels(String s) {
     char [] cs=s.toCharArray();
     int n=s.length();
     int l=0, r= n - 1;
     while (l < r){
         if (hash[cs[l] - ' '] && hash[cs[r] - ' ']) {
             swap(cs, l++, r--);
         } else {
             if (!hash[cs[l] - ' ']) l++;
             if (!hash[cs[r] - ' ']) r--;
         }
     }
        return String.valueOf(cs);
    }

    public void swap(char[] cs,int l ,int r){
        char c = cs[l];
        cs[l] = cs[r];
        cs[r] = c;
    }
}
