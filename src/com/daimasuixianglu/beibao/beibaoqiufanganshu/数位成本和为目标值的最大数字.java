package com.daimasuixianglu.beibao.beibaoqiufanganshu;

import java.util.Arrays;

/**
 * 给你一个整数数组 cost 和一个整数 target。请你返回满足如下规则可以得到的 最大 整数：
 * 给当前结果添加一个数位 (i+1) 的成本为 cost[i] （ 数组下标从 0 开始）
 * 总成本必须恰好等于 target
 * 添加的数位中没有数字 0
 * 由于答案可能会很大，请你以字符串形式返回。
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 * 示例 1：
 * 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * 输出："7772"
 * 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要求的数字，但 "7772" 是较大的数字。
 *  数字     成本
 *   1  ->   4
 *   2  ->   3
 *   3  ->   2
 *   4  ->   5
 *   5  ->   6
 *   6  ->   7
 *   7  ->   2
 *   8  ->   5
 *   9  ->   5
 */
public class 数位成本和为目标值的最大数字 {
    public String largestNumber(int[] cost, int t) {
        int f[]=new int[t+1];
        Arrays.fill(f,Integer.MIN_VALUE);
        f[0]=0;
        for (int i = 0; i <=9 ; i++) {
            int u=cost[i-1];
            for (int j = u; j <= t ; j++) {
                f[j]=Math.max(f[j],f[j-u]+1);
            }
        }
        if (f[t]<0) return "0";
        String ans="";
        for (int i = 0,j=t;i>=1; i--) {
            int u=cost[i-1];
            while (j>=u && f[j] ==f[j-u]+1){
                ans+=String.valueOf(i);
                j-=u;
            }
        }
        return ans;
    }

}
