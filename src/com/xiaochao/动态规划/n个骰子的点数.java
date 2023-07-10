package com.xiaochao.动态规划;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * 示例 1:
 * 输入: 1
 * 输出:
 * [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * 输入: 2
 * 输出:
 * [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * 限制：
 * 1 <= n <= 11
 * @author 15983
 */
public class n个骰子的点数 {

    public static  double[] dicesProbability(int n) {
        int[][] dp=new int[n+1][6*n+1];
        double[] res=new double[6*n+1];
        double All=Math.pow(6,n);
        for(int j=1;j<=6;j++){
            dp[1][j]=1;
        }
        for(int i=2;i<=n;i++){
            for(int j=6*n;j>=1;j--){
                for(int k=1;k<=6;k++){
                    if(j>=k){
                        dp[i][j]+=dp[i-1][j-k];
                    }
                }
            }
        }
        for(int i=0;i<=5*n;i++){
            res[i]=dp[n][i+n]/All;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 2;
        double[] doubles = dicesProbability(a);
        for (double aDouble : doubles) {
            System.out.print(aDouble);
        }
    }
}
