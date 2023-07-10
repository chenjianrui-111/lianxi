package com.xiaochao.DP.数位dp;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 * 示例 1：
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 */
public class 单词拆分二 {
    List<String> res = new LinkedList<>();
    // 记录回溯路径
    LinkedList<String> track = new LinkedList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        // 根据函数定义，判断 s[0..] 是否能够被拼出
        backtrack(s, 0, wordDict);
        return res;
    }

    // 回溯算法框架
    void backtrack(String s, int i, List<String> wordDict) {
        // base case，整个 s 都被拼出来了
        if (i == s.length()) {
            res.add(String.join(" ", track));
            return;
        }
        if (i > s.length()) {
            return;
        }

        // 遍历所有单词，尝试匹配 s[i..] 的前缀
        for (String word : wordDict) {
            int len = word.length();
            // 单词太长，跳过
            if (i + len > s.length()) {
                continue;
            }
            // 无法匹配，跳过
            String subStr = s.substring(i, i + len);
            if (!subStr.equals(word)) {
                continue;
            }
            // s[i..] 的前缀被 word 匹配，做选择
            track.addLast(word);
            backtrack(s, i + len, wordDict);
            // 撤销选择
            track.removeLast();
        }
    }
}
