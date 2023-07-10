package com.leixing.滑动窗口;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 104。
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 双指针
 * 令 l 为符合条件的子串的左端点，r 为符合条件的子串的右端点。
 * 使用 cnt 统计 [l,r] 范围的子串中每个字符串出现的次数。
 * 对于合法的子串而言，必然有 sum(所有字符的出现次数) - max(出现次数最多的字符的出现次数）= other(其他字符的出现次数) <= k。
 * 当找到这样的性质之后，我们可以对 s 进行遍历，每次让 r 右移并计数，如果符合条件，更新最大值；
 * 如果不符合条件，让 l 右移，更新计数，直到符合条件。
 */
public class 替换后的最长重复字符 {
    public int characterReplacement(String s, int k) {
      char cs[] =s.toCharArray();
      int [] cnt=new int[26];
      int ans=0;
      for (int l=0,r=0;r<s.length();r++){
          int cur=cs[r] -'A';
          cnt[cur]++;
          while (!check(cnt,k)){
              int del=cs[l] - 'A';
              cnt[del]--;
              l++;
          }
          ans=Math.max(ans,r-l+1);
      }
      return ans;
    }
    boolean check(int [] cnt,int k){
        int max=0,sum=0;
        for (int i=0;i< 26;i++){
            max=Math.max(max,cnt[i]);
            sum+=cnt[i];
        }
        return  sum-max <=k;
    }
}
