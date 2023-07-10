package com.leixing.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * 朴素哈希表
 * 令 n 为字符串 s 的长度，m 为数组 words 的长度（单词的个数），w 为单个单词的长度。
 * 由于 words 里面每个单词长度固定，而我们要找的字符串只能恰好包含所有的单词，所有我们要找的目标子串的长度为 m * m ∗ w。
 * 那么一个直观的思路是：
 * 使用哈希表 map 记录 words 中每个单词的出现次数
 * 枚举 s 中的每个字符作为起点，往后取得长度为 m * wm∗w 的子串 sub
 * 使用哈希表 cur 统计 sub 每个单词的出现次数（每隔 w 长度作为一个单词）
 * 比较 cur 和 map 是否相同
 * 注意：在步骤 3 中，如果发现 sub 中包含了 words 没有出现的单词，可以直接剪枝。
 * 剪枝处使用了带标签的 continue 语句直接回到外层循环进行。
 */
public class 串联所有单词的子串 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans=new ArrayList<>();
        if (words.length == 0) return ans;
        int n=s.length(),m=words.length,w=words[0].length();
        Map<String,Integer> map=new HashMap<>();
        for (String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }

        out:for (int i=0;i+m * w<=n;i++){
            Map<String,Integer> cur=new HashMap<>();
            String sub=s.substring(i,i+m*w);
            for (int j=0;j<sub.length();j+=w){
                String item=sub.substring(j,j+w);
                if (!map.containsKey(item)) continue out;
                cur.put(item,cur.getOrDefault(item,0)+1);
            }
            if (cmp(cur,map)) ans.add(i);
        }
        return ans;
    }
    boolean cmp(Map<String, Integer> m1, Map<String, Integer> m2) {
        if (m1.size() != m2.size()) return false;
        for (String k1 : m1.keySet()) {
            if (!m2.containsKey(k1) || !m1.get(k1).equals(m2.get(k1))) return false;
        }
        for (String k2 : m2.keySet()) {
            if (!m1.containsKey(k2) || !m1.get(k2).equals(m2.get(k2))) return false;
        }
        return true;
    }
}
/**
 * 时间复杂度：将 words 中的单词存入哈希表，复杂度为 O(m)；然后第一层循环枚举 s 中的每个字符作为起点，
 * 复杂度为 O(n)；在循环中将 sub 划分为 m 个单词进行统计，枚举了 m - 1 个下标，复杂度为 O(m)；
 * 每个字符串的长度为 w。整体复杂度为 O(n * m * w)
 * 空间复杂度：O(m * w)
 */
