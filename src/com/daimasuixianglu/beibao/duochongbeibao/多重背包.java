package com.daimasuixianglu.beibao.duochongbeibao;

import java.util.ArrayList;
import java.util.List;

/**
 * 有 N 种物品和一个容量为 C 的背包，每种物品「数量有限」。
 * 第 i 件物品的体积是 v[i]，价值是w[i]，数量为 s[i]。
 * 问选择哪些物品，每件物品选择多少件，可使得总价值最大。
 * 其实就是在 0-1 背包问题的基础上，增加了每件物品可以选择「有限次数」的特点（在容量允许的情况下）。
 * 示例 1：
 * 输入: N = 2, C = 5, v = [1,2], w = [1,2], s = [2,1]
 * 输出: 4
 * 解释: 选两件物品 1，再选一件物品 2，可使价值最大。
 * 朴素二维
 * 在之前的章节我们说过，几乎所有的「背包问题」都是基于「01 背包」演变而来。
 * 因此，当我们确定一个问题是背包问题之后，可以根据其物品的「数量限制」来判别是何种背包问题，然后套用「01 背包」的思路来求解。
 * 具体的，我们可以套用「01 背包」的「状态定义」来进行分析：
 * dp[i][j]代表考虑前 i 件物品，且所选物品总体积不超过 j 时获得的最大价值。
 * 由于每件物品可以被选择「有限次」，因此对于某个 dp[i][j] 而言，其值应该为以下所有可能方案中的最大值：
 * 选择 0 件物品 i  的最大价值，即dp[i-1][j]
 * 选择 1 件物品 i 的最大价值，即dp[i-1][j-v[i]]+w[i]
 * 选择 2 件物品 i 的最大价值，即dp[i-1][j-2*v[i]]+2*w[i]
 * 选择 s[i] 件物品 i 的最大价值，dp[i-1][j-s[i]*v[i]]+s[i]*w[i]
 * 由此我们可以得出「状态转移方程」为：dp[i][j]=max(dp[i-1][j],dp[i-1][j-k*v[i]]+k*w[i]),0<k<=s[i],0<k*v[i]<=j
 * 可以发现其状态转移方程与 完全背包 完全一致，只是多了0<k<=s[i]  的条件。
 * 也好理解，毕竟「完全背包」不限制物品数量，「多重背包」限制物品数量。
 */
public class 多重背包 {
    public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
        int[][]dp=new int[N][C+1];
        //先处理第一件那物品
        for (int j = 0; j <=C ; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件（不超过限制数量）
            int maxK=Math.min(j/v[0],s[0]);
            dp[0][j]=maxK * w[0];
        }
        //处理剩余物品
        for (int i = 1; i <N ; i++) {
            for (int j = 0; j <=C ; j++) {
                // 不考虑第 i 件物品的情况
                int n=dp[i-1][j];
                // 考虑第 i 件物品的情况
                int y=0;
                for (int k = 1; k <=s[i] ; k++) {
                    if (j < k*v[i]){
                        break;
                    }
                    y=Math.max(y,dp[i-1][j-k*v[i]]+k*w[i]);
                }
                dp[i][j]=Math.max(n,y);
            }
        }
        return dp[N-1][C];
    }
}
//时间复杂度：共有三层循环的运算量。由于每种情况都需要被考虑到，所以各维度是累乘关系 n*c*s，即 ，其中 。整体复杂度为
//空间复杂度：O(n*c)
/**
 * 滚动数组
 * 通过观察我们的「状态转移方程」可以发现，我们在更新某个 dp[i][j] 的时候只依赖于 dp[i-1][y]。
 * 因此我们可以像 01 背包那样使用「滚动数组」的方式将空间优化到 O(C)。
 */
class Solution {
    public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
        int[][] dp = new int[2][C + 1];

        // 先处理第一件物品
        for (int j = 0; j <= C; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件（不超过限制数量）
            int maxK = Math.min(j / v[0], s[0]);
            dp[0][j] = maxK * w[0];
        }

        // 处理剩余物品
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况
                int n = dp[(i- 1)&1][j];
                // 考虑第 i 件物品的情况
                int y = 0;
                for (int k = 1; k <= s[i]; k++) {
                    if (j < k * v[i]) {
                        break;
                    }
                    y = Math.max(y, dp[(i - 1)&1][j - k * v[i]] + k * w[i]);
                }
                dp[i&1][j] = Math.max(n, y);
            }
        }

        return dp[(N - 1)&1][C];
    }
}

/**
 *
 一维空间优化
 在之前的「01 背包」和「完全背包」都可以进行「一维空间优化」。
 其中「完全背包」的「一维空间优化」还能有效降低时间复杂度。
 那么「多重背包」是否也能通过「一维空间优化」来降低时间复杂度呢？
 答案是可以优化空间，但是不能降低时间复杂度。
 因为当我们像「完全背包」那样只保留「容量维度」，并且「从小到大」遍历容量的话，我们在转移 F[j] 时是无法直接知道所依赖的 f[j-v[i]] 到底使用了多少件物品 i 的。
 这个问题在「完全背包」里面无须关心，因为每件物品可以被选择无限次，而在「多重背包」则是不能忽略，否则可能会违背物品件数有限的条件
 因此，「多重背包」问题的「一维空间优化」并不能像「完全背包」那样使复杂度降低。
 */
class Solution01 {
    public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            for (int j = C; j >= v[i]; j--) {
                for (int k = 0; k <= s[i] && j >= k * v[i]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[C];
    }
}
/**
 二进制优化
 二进制优化可以使得我们能解决的多重背包问题数量级从  上升为 。
 所谓的「二进制优化」其实是指，我们如何将一个多重背包问题彻底转化为 0-1 背包问题，同时降低其复杂度。
 在上一节我们的扁平化方式是直接展开，一个数量为  的物品等效于  。
 这样并没有减少运算量，但是如果我们能将  变成小于  个数，那么这样的「扁平化」就是有意义的。
 学过 Linux 的都知道文件权限最高是 7，代表拥有读、写、执行的权限，但其实这个 7 是对应了 1、2、4 三个数字的，也就是 r:1、w:2、x:4 ，三种权限的组合共有 8 种可能性。
 这里就采用了 3 个数来对应 8 种情况的“压缩”方法。
 我们也可以借鉴这样的思路：将原本为 n 的物品用 ceil(log(n)) 个数来代替，从而降低算法复杂度。
 7 可以用 1、2、4 来代替，像刚刚提到的 10 ，我们可以使用 1、2、4、3 来代替，你可能会有疑问，为什么是 1、2、4、3，而不是 1、2、4、6 或者 1、2、4、8 呢？
 其实把他们几个数加起来就知道了，1、2、4、6 可以表达的范围是 0~13，而 1、2、4、8 可以表达的范围是 0~15，而我们要求的是表达 10，大于 10 的范围是不能被选择的。
 所以我们可以在 1、2、4 （表达的范围是 0~7）的基础上，增加一个数 3（由 10 - 7 而来），这样就能满足我们需要表达的范围 0~10。
 */
class Solution03 {
    public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
        // 扁平化
        List<Integer> worth = new ArrayList<>();
        List<Integer> volume = new ArrayList<>();

        // 我们希望每件物品都进行扁平化，所以首先遍历所有的物品
        for (int i = 0; i < N; i++) {
            // 获取每件物品的出现次数
            int val = s[i];
            // 进行扁平化：如果一件物品规定的使用次数为 7 次，我们将其扁平化为三件物品：1*重量&1*价值、2*重量&2*价值、4*重量&4*价值
            // 三件物品都不选对应了我们使用该物品 0 次的情况、只选择第一件扁平物品对应使用该物品 1 次的情况、只选择第二件扁平物品对应使用该物品 2 次的情况，只选择第一件和第二件扁平物品对应了使用该物品 3 次的情况 ...
            for (int k = 1; k <= val; k *= 2) {
                val -= k;
                worth.add(w[i] * k);
                volume.add(v[i] * k);
            }
            if (val > 0) {
                worth.add(w[i] * val);
                volume.add(v[i] * val);
            }
        }
        // 0-1 背包问题解决方案
        int[] dp = new int[C + 1];
        for (int i = 0; i < worth.size(); i++) {
            for (int j = C; j >= volume.get(i); j--) {
                dp[j] = Math.max(dp[j], dp[j - volume.get(i)] + worth.get(i));
            }
        }
        return dp[C];
    }
}
/**
 *二进制优化的本质，是对「物品」做分类，使得总数量为 m 的物品能够用更小的 log m 个数所组合表示出来。
 * 而单调队列优化，某种程度上也是利用「分类」实现优化。只不过不再是针对「物品」做分类，而是针对「状态」做分类。
 */
