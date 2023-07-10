package com.leixing.滑动窗口;

/**
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，
 * 返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * 提示：
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 *
 * 滑动窗口
 * 由于是 s2 中判断是否包含 s1 的排列，而且 s1 和 s2 均为小数。
 * 可以使用数组先对 s1 进行统计，之后使用滑动窗口进行扫描，每滑动一次检查窗口内的字符频率和 s1
 * 是否相等 ~
 * 以下代码，可以作为滑动窗口模板使用：
 * PS. 你会发现以下代码和 643. 子数组最大平均数 I 和 1423. 可获得的最大点数 代码很相似，
 * 因为是一套模板。
 * 初始化将滑动窗口压满，取得第一个滑动窗口的目标值
 * 继续滑动窗口，每往前滑动一次，需要删除一个和添加一个元素
 */
public class 字符串的排列 {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m > n) return false;
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            cnt[c - 'a']++;
        }
        int[] cur = new int[26];
        for (int i = 0; i < m; i++) {
            cur[s2.charAt(i) - 'a']++;
        }
            if (check(cnt, cur)) return true;
            for (int i = m; i < n; i++) {
                cur[s2.charAt(i) - 'a']++;
                cur[s2.charAt(i - m) - 'a']--;
                if (check(cur, cnt)) return true;
            }
        return false;
    }
     boolean check(int [] cnt1,int [] cnt2){
         for (int i=0;i< 26;i++){
             if (cnt1[i] !=cnt2[i])
                 return false;
         }
         return true;
        }


}
