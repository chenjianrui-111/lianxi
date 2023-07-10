package com.leixing.动态规划.序列DP;

/**
 *给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 * 动态规划【空格技巧】
 * 这是一道「最长公共子序列（LCS）」的裸题。
 * 对于这类题的都使用如下「状态定义」即可：
 * f[i][j] 代表考虑 s1 的前 i 个字符、考虑 s2 的前 j 的字符，形成的最长公共子序列长度。
 * 当有了「状态定义」之后，基本上「转移方程」就是呼之欲出：
 * s1[i]==s2[j] : f[i][j]=f[i-1][j-1]+1。代表必然使用 s1[i] 与 s2[j] 时 LCS 的长度。
 * s1[i]!=s2[j] : f[i][j]=max(f[i-1][j], f[i][j-1])。代表必然不使用 s1[i]（但可能使用s2[j]）时 和 必然不使用 s2[j]（但可能使用s1[i]）时 LCS 的长度。
 * 一些编码细节：
 * 通常我会习惯性往字符串头部追加一个空格，以减少边界判断（使下标从 1 开始，并很容易构造出可滚动的「有效值」）。
 */
public class 两个字符串的删除操作 {
    public int minDistance(String word1, String word2) {
        int n=word1.length(),m=word2.length();
        word1=word1+" ";word2=word2+" ";
        char[] cs1=word1.toCharArray(),cs2=word2.toCharArray();
        int [][] f=new int[n+1][m+1];

        //因为有了追加的空格，我们有了显然的初始化值（以下两种初始化方式均可）
        // for (int i = 0; i <= n; i++) Arrays.fill(f[i], 1);
        for (int i = 0; i <= n; i++) f[i][0] = 1;
        for (int j = 0; j <= m; j++) f[0][j] = 1;

        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (cs1[i] == cs2[j]){
                    f[i][j]=f[i-1][j-1]+1;
                }else {
                    f[i][j]=Math.max(f[i-1][j],f[i][j-1]);
                }
            }
        }
        // 减去最开始追加的空格
        return f[n][m] - 1;
    }
}
//时间复杂度：O(n * m)
//空间复杂度：O(n * m)
