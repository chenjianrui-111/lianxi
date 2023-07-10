package com.xiaochao.字符串;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。
 * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：s = "barfoothefoobarman",
 *      words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword",
 *      words = ["word","good","best","word"]
 * 输出：[]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 朴素哈希表
 令 n 为字符串 s 的长度，m 为数组 words 的长度（单词的个数），w 为单个单词的长度。
 由于 words 里面每个单词长度固定，而我们要找的字符串只能恰好包含所有的单词，所有我们要找的目标子串的长度为 。
 那么一个直观的思路是：
 使用哈希表 map 记录 words 中每个单词的出现次数
 枚举 s 中的每个字符作为起点，往后取得长度为  的子串 sub
 使用哈希表 cur 统计 sub 每个单词的出现次数（每隔 w 长度作为一个单词）
 比较 cur 和 map 是否相同
 注意：在步骤 3 中，如果发现 sub 中包含了 words 没有出现的单词，可以直接剪枝。
 剪枝处使用了带标签的 continue 语句直接回到外层循环进行。
 */
public class 串联所有单词的子串 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (words.length == 0) return ans;
        int n = s.length(), m = words.length, w =words[0].length();
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word,map.getOrDefault(word,0) + 1);
        }
        out:for (int i = 0;i + m * w <= n;i++){
            Map<String,Integer> cur =  new HashMap<>();
            String sub =s.substring(i, i + m * w);
            for (int j = 0; j < sub.length() ; j += w) {
                String item = sub.substring(j , j + w);
                if (!map.containsKey(item)) continue out;
                cur.put(item,cur.getOrDefault(item,0) + 1);
            }
            if (cmp(cur,map)) ans.add(i);
        }
        return ans;
    }
    boolean cmp(Map<String,Integer> m1,Map<String,Integer> m2){
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
//时间复杂度：将 words 中的单词存入哈希表，复杂度为 O(M) ；然后第一层循环枚举 s 中的每个字符作为起点，复杂度为 O(N)；
// 在循环中将 sub 划分为 m 个单词进行统计，枚举了 m - 1 个下标，复杂度为 ；每个字符串的长度为 w。整体复杂度为O(M * N * W)
//空间复杂度：O(M * N)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (words.length == 0) return ans;

        int n = s.length(), m = words.length, w = words[0].length();

        // 统计 words 中「每个目标单词」的出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < w; i++) {
            // 构建一个当前子串对应 map，统计当前子串中「每个目标单词」的出现次数
            Map<String, Integer> curMap = new HashMap<>();
            // 滑动窗口的大小固定是 m * w
            // 每次将下一个单词添加进 cur，上一个单词移出 cur
            for (int j = i; j + w <= n; j += w) {
                String cur = s.substring(j, j + w);
                if (j >= i + (m * w)) {
                    int idx = j - m * w;
                    String prev = s.substring(idx, idx + w);
                    if (curMap.get(prev) == 1) {
                        curMap.remove(prev);
                    } else {
                        curMap.put(prev, curMap.get(prev) - 1);
                    }
                }
                curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);
                // 如果当前子串对应 map 和 words 中对应的 map 相同，说明当前子串包含了「所有的目标单词」，将起始下标假如结果集
                if (map.containsKey(cur) && curMap.get(cur).equals(map.get(cur)) && cmp(map, curMap)) {
                    ans.add(j - (m - 1) * w);
                }
            }
        }

        return ans;
    }
    // 比较两个 map 是否相同
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
//时间复杂度：将 words 中的单词存入哈希表，复杂度为O(M) ；然后枚举了取余的结果，复杂度为 O(W) ；每次循环最多处理 n 长度的字符串，复杂度为O(N) 。整体复杂度为 O(M + W * N)
//空间复杂度：O(M*W)
