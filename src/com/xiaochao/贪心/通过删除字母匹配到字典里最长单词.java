package com.xiaochao.贪心;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，
 * 该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * 示例 1：
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * 提示：
 * • 1 <= s.length <= 1000
 * • 1 <= dictionary.length <= 1000
 * • 1 <= dictionary[i].length <= 1000
 * • s 和 dictionary[i] 仅由小写英文字母组成
 */

import java.util.Collections;
import java.util.List;

/**
 * 排序 + 双指针 + 贪心
 * 根据题意，我们需要找到 dictionary 中为 s 的子序列，且「长度最长（优先级 1）」及「字
 * 典序最小（优先级 2）」的字符串。
 * 数据范围全是 1000。 我们可以先对 dictionary 根据题意进行自定义排序：
 * 1. 长度不同的字符串，按照字符串长度排倒序；
 * 2. 长度相同的，则按照字典序排升序。
 * 然后我们只需要对 dictionary 进行顺序查找，找到的第一个符合条件的字符串即是答案。
 * 具体的，我们可以使用「贪心」思想的「双指针」实现来进行检查：
 * 1. 使用两个指针 i 和 j 分别代表检查到 s 和 dictionary[x] 中的哪位字符；
 * 2. 当 s[i] != dictionary[x][j] ，我们使 i 指针右移，直到找到 s 中第一位与
 * dictionary[x][j] 对得上的位置，然后当 i 和 j 同时右移，匹配下一个字符；
 * 3. 重复步骤 2，直到整个 dictionary[x] 被匹配完。
 * 证明：对于某个字符 dictionary[x][j] 而言，选择 s 中 当前 所能选择的下标最小的位
 * 置进行匹配，对于后续所能进行选择方案，会严格覆盖不是选择下标最小的位置，因此结果不会变差
 */
public class 通过删除字母匹配到字典里最长单词 {
    public String findLongestWord(String s, List<String> list) {
        Collections.sort(list,(a,b)->{
            if (a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });
        int n = s.length();
        for (String ss : list) {
            int m = ss.length();
            int i = 0, j = 0;
            while (i < n && j < m){
                if (s.charAt(i) == ss.charAt(j)) j++;
                i++;
            }
            if (j == m) return ss;
        }
        return "";
    }
}
//• 时间复杂度：令 n 为 s 的长度， m 为 dictionary 的长度。排序复杂度为 O(m log m)；
// 对 dictionary 中的每个字符串进行检查，单个字符串的检查复杂 度为 O(min(n, dictionary[i])) ≈ O(n)。
// 整体复杂度为 O(m log m + m ∗ n) • 空间复杂度：O(log m)
