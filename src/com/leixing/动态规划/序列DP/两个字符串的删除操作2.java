package com.leixing.动态规划.序列DP;

/**
 * 动态规划【利用偏移】
 * 上述「追加空格」的做法是我比较习惯的做法 🤣
 * 事实上，我们也可以通过修改「状态定义」来实现递推：
 * f[i][j] 代表考虑 s1s1 的前 i - 1个字符、考虑 s2 的前 j - 1 的字符，形成的最长公共子序列长度。
 * 那么最终的 f[n][m] 就是我们的答案，f[0][0] 当做无效值，不处理即可。
 * s1[i-1]==s2[j-1] : f[i][j]=f[i-1][j-1]+1。代表使用 s1[i-1] 与 s2[j-1]形成最长公共子序列的长度。
 * s1[i-1]!=s2[j-1] : f[i][j]=max(f[i-1][j], f[i][j-1])。代表不使用 s1[i-1] 形成最长公共子序列的长度、不使用s2[j−1] 形成最长公共子序列的长度。这两种情况中的最大值。
 */
public class 两个字符串的删除操作2 {
    public int longestCommonSubsequence(String s1, String s2) {
        int n=s1.length(),m=s2.length();
        char[] cs1=s1.toCharArray(),cs2=s2.toCharArray();
        int[][] f=new int[n+1][m+1];
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (cs1[i - 1]==cs2[j - 1]){
                    f[i][j] =f[i-1][j-1] +1;
                }else {
                    f[i][j] =Math.max(f[i-1][j],f[i][j-1]);
                }
            }
        }
        return f[n][m];
    }
}
//时间复杂度：O(n * m)
//空间复杂度：O(n * m)
