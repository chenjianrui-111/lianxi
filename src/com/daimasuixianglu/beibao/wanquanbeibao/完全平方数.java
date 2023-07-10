package com.daimasuixianglu.beibao.wanquanbeibao;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定正整数 ，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 。
 * 你需要让组成和的完全平方数的个数最少。
 * 给你一个整数  ，返回和为  的完全平方数的「最少数量」。
 * 「完全平方数」是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 提示：
 * 1 <= n <=10^4
 */
public class 完全平方数 {
    int INF=-1;
    public int numSquares(int n){
        List<Integer> list=new ArrayList<>();
        int idx=1;
        while (idx * idx <=n){
            list.add(idx*idx);
            idx++;
        }
        // f[i][j] 代表考虑前 i 个物品，凑出 j 所使用到的最小元素个数
        int len=list.size();
        int[][] f=new int[len][n+1];
        // 处理第一个数的情况
        for (int j = 0; j <n ; j++) {
            int t=list.get(0);
            int k=j/t;
            if (k*t==j){ // 只有容量为第一个数的整数倍的才能凑出
                f[0][j]=k;
            }else {
                f[0][j]=INF;
            }
        }
        // 处理剩余数的情况
        for (int i = 1; i <len ; i++) {
            int t=list.get(i);
            for (int j = 0; j <n ; j++) {
                //对于不选第i个数的情况
                f[i][j]=f[i-1][j];
                // 对于选 k 次第 i 个数的情况
                for (int k=1;k*t<j;k++){
                    // 能够选择 k 个 t 的前提是剩余的数字 j - k * t 也能被凑出
                    if (f[i-1][j-k*t]!=INF){
                        f[i][j]=Math.min(f[i][j],f[i-1][j-k*t]+k);
                    }
                }
            }
        }
        return f[len-1][n];
    }
}
class Solution02{
    int INF = -1;
    public int numSquares(int n) {
        // 预处理出所有可能用到的「完全平方数」
        List<Integer> list = new ArrayList<>();
        int idx = 1;
        while (idx * idx <= n) {
            list.add(idx * idx);
            idx++;
        }

        // f[j] 代表考虑到当前物品为止，凑出 j 所使用到的最小元素个数
        int len = list.size();
        int[] f = new int[n + 1];

        // 处理第一个数的情况
        for (int j = 0; j <= n; j++) {
            int t = list.get(0);
            int k = j / t;
            if (k * t == j) { // 只有容量为第一个数的整数倍的才能凑出
                f[j] = k;
            } else { // 其余则为无效值
                f[j] = INF;
            }
        }

        // 处理剩余数的情况
        for (int i = 1; i < len; i++) {
            int t = list.get(i);
            for (int j = t; j <= n; j++) {
                // 当不更新 f[j] 的时候，对应了二维表示中的 f[i - 1][j]

                // 可以更新 f[j] 的前提是：剩余的 j - k * t 也能够被凑出
                // 更新 f[j] 所依赖的 f[j - t] 对应了二维表示中的 f[i - 1][j - k * t]
                if (f[j - t] != INF) f[j] = Math.min(f[j], f[j - t] + 1);
            }
        }

        return f[n];
    }
}
