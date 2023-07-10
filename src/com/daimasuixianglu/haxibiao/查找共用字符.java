package com.daimasuixianglu.haxibiao;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 *提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 由小写英文字母组成
 *
 */
public class 查找共用字符 {
    public List<String> commonChars(String[] words) {
        List<String> result=new ArrayList<>();
        if (words.length==0) return result;
        // 用来统计所有字符串里字符出现的最小频率
        int[]hash=new int[26];
        // 用第一个字符串给hash初始化
        for (int i = 0; i <words[0].length() ; i++) {
            hash[words[0].charAt(i)-'a']++;
        }
        // 统计除第一个字符串外字符的出现频率
        for (int i = 1; i <words.length ; i++) {
            int [] hashOtherStr=new int[26];
            for (int j = 0; j <words[0].length() ; j++) {
                hashOtherStr[words[i].charAt(j)- 'a']++;
            }
            // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        // 将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) { // 注意这里是while，多个重复的字符
                char c= (char) (i+'a');// char -> string
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;
    }
}
