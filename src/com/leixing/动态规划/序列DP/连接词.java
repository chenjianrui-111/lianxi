package com.leixing.动态规划.序列DP;

import java.util.*;

/**
 *给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * 示例 1：
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 *      "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 *      "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 * 示例 2：
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 * 提示：
 * 1 <= words.length <= 104
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 105
 * 序列 DP + 字符串哈希
 * 给定数组 words，先考虑如何判断某个 s=words[i] 是否为「连接词」。
 * 为了方便，我们称组成 s 的每个连接部分为 item。
 * 举个 🌰，例如 s = abc，其可能的 item 组合为 a 和 bc。
 * 判断单个字符串是否为连接词可使用动态规划求解：定义 f[i] 为考虑 s 的前 i 个字符（令下标从 1 开始），能够切分出的最大 item 数的个数。
 * 这里之所以采用「记录 f[i] 为最大分割 item 数（int 类型动规数组）」，而不是「记录 f[i] 为是否可由多个 item 组成（bool 类型动规数组）」，是因为每个 s = words[i] 至少可由自身组成，采用 bool 记录状态的话，最终 f[n] 必然为 True，需要额外处理最后一个状态，干脆记录最大分割数量好了。此时如果 s 为「连接词」必然有 f[n] > 1f[n]>1。
 * 不失一般性的考虑 f[i] 该如何转移：假设 f[i] 可由 f[j] 转移而来（其中 j < i），那么能够转移的充要条件为 f[j] != 0f[j]!=0 且子串 s[(j + 1)..i]s[(j+1)..i] 在 words 出现过。
 * 其中枚举 i 和 j 的复杂度已经去到 O(n^2)
 *  了，如果常规通过 HashMap 等数据结构判断某个字符串是否存在，执行哈希函数时需要对字符进行遍历，整体复杂度去到了 O(n^3)会 TLE。
 * 我们通过「字符串哈希」方式来优化判断某个子串是否存在于 words 中。
 * 具体的，在判断每个 s = words[i] 是否为为连接词前，先对 word 进行遍历，预处理每个 words[i] 的哈希值，并存入 HashSet 中，这样我们将「判断某个子串是否存在于 words」的问题转化为「判断某个数值是否存在于 Set 当中」。
 * 又由于 我们在计算某个子串 s 的哈希值时，是从前往后处理每一位的 s[i]，因此在转移 f[i] 时，我们期望能够从前往后处理子串，这是常规的从 [0, i - 1] 范围内找可转移点 f[j] 无法做到的。
 * 所以 我们调整转移逻辑为：从 f[i] 出发，枚举范围 [i + 1, n]，找到可由 f[i] 所能更新的状态 f[j]，并尝试使用 f[i] 来更新 f[j]。转移方程为：
 * f[j]=max(f[j],f[i]+1)
 * 当然，能够转移的前提条件为 f[i] 为有效值，且子串 s[(i + 1), j] 在 words 出现过。
 * 一些细节：为了方便，我们定义 f[i] = -1 为无效状态；
 * 另外由于字符串哈希会产生哈希碰撞，这里在计算哈希值的时候，修改了一下哈希计算方式（额外增加了一个 OFFSET），当时的目的是想在电脑没电前 AC，而另一个更加稳妥的方式是使用双哈希，或是干脆记录某个哈希值对应了哪些字符串。
 */
public class 连接词 {
    class Solution {
        Set<Long> set = new HashSet<>();
        int P = 131, OFFSET = 128;
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            for (String s : words) {
                long hash = 0;
                for (char c : s.toCharArray()) {
                    hash = hash * P + (c - 'a') + OFFSET;
                }
                set.add(hash);
            }
            List<String> ans = new ArrayList<>();
            for (String s : words) {
                if (check(s)) ans.add(s);
            }
            return ans;
        }
        boolean check(String s) {
            int n = s.length();
            int[] f = new int[n + 1];
            Arrays.fill(f, -1);
            f[0] = 0;
            for (int i = 0; i <= n; i++) {
                if (f[i] == -1) continue;
                long cur = 0;
                for (int j = i + 1; j <= n; j++) {
                    cur = cur * P + (s.charAt(j - 1) - 'a') + OFFSET;
                    if (set.contains(cur)) f[j] = Math.max(f[j], f[i] + 1);
                }
                if (f[n] > 1) return true;
            }
            return false;
        }
    }
}
