package com.leixing.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * 提示：
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 *
 * 滑动窗口 + 哈希表
 * 数据范围只有 10^5
 * 一个朴素的想法是：从左到右处理字符串 s，使用滑动窗口得到每个以 s[i] 为结尾且长度为 10 的子串，
 * 同时使用哈希表记录每个子串的出现次数，如果该子串出现次数超过一次，则加入答案。
 * 为了防止相同的子串被重复添加到答案，而又不使用常数较大的 Set 结构。我们可以规定：
 * 当且仅当该子串在之前出现过一次（加上本次，当前出现次数为两次）时，将子串加入答案。
 */
public class 重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans=new ArrayList<>();
        int len=s.length();
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i + 10 <=len;i++){
            String cur=s.substring(i,i+10);
            int cnt=map.getOrDefault(cur,0);
            if (cnt == 1) ans.add(cur);
            map.put(cur,cnt+1);
        }
        return ans;
    }
}
