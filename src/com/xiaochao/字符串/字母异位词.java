package com.xiaochao.字符串;

/**
 * 说明：你可以假设字符串只包含小写字母。
 * 示例 1
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 字母异位词，也就是两个字符串中的相同字符的数量要对应相等。例如，s 等于 “anagram”，t 等于 “nagaram”，
 * s 和 t 就互为字母异位词。因为它们都包含有三个字符 a，一个字符 g，一个字符 m，一个字符 n，以及一个字符 r。
 * 而当 s 为 “rat”，t 为 “car”的时候，s 和 t 不互为字母异位词。
 */

/**
 * 解题思路
 * 一个重要的前提“假设两个字符串只包含小写字母”，小写字母一共也就 26 个，因此：
 * 1.可以利用两个长度都为 26 的字符数组来统计每个字符串中小写字母出现的次数，然后再对比是否相等；
 * 2.可以只利用一个长度为 26 的字符数组，将出现在字符串 s 里的字符个数加 1，而出现在字符串 t 里的字符个数减 1，
 *  最后判断每个小写字母的个数是否都为 0。
 */
public class 字母异位词 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] alpha = new int[26];
        for (int i = 0; i < s.length() ; i++) {
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (alpha[i] != 0)
                return false;
        }
        return true;
    }
}
