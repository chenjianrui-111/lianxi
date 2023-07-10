package com.cjr.tu;

import java.util.Scanner;

/**
 * DNA 是由 ACGT 四种核苷酸组成，例如 AAAGTCTGAC，假定自然环境下 DNA 发生异变的情况有：
 * 基因缺失一个核苷酸
 * 基因新增一个核苷酸
 * 基因替换一个核苷酸
 * 且发生概率相同。
 * 古生物学家 Sam 得到了若干条相似 DNA 序列，Sam 认为一个 DNA 序列向另外一个 DNA 序列转变所需的最小异变情况数可以代表其物种血缘相近程度，
 * 异变情况数越少，血缘越相近，请帮助 Sam 实现获取两条 DNA 序列的最小异变情况数的算法。
 * 格式：
 * 输入：
 * - 每个样例只有一行，两个 DNA 序列字符串以英文逗号“,”分割
 * 输出：
 * - 输出转变所需的最少情况数，类型是数字
 * 示例：
 * 输入：ACT,AGCT
 * 输出：1
 * 提示：
 * 每个 DNA 序列不超过 100 个字符
 *
 * 假设存在两个字符串A和B，他们的长度分别是lenA和lenB。首先考虑第一个字符，由于他们是一样的，所以只需要计算A[2...lenA]和B[2...lenB]之间的距离即可。那么如果两个字符串的第一个字符不一样,有以下三种方案：
 *
 * 修改A串的第一个字符成B串的第一个字符，之后仅需要计算A[2...lenA]和B[2...lenB]的距离即可；
 * 删除A串的第一个字符，之后仅需要计算A[2...lenA]和B[1...lenB]的距离即可；
 * 把B串的第一个字符插入到A串的第一个字符之前，之后仅需要计算A[1...lenA]和B[2...lenB]的距离即可。
 */
public class 古生物血缘远近判定 {

    private static int minNum(String pre, String cur){
        int preLen = pre.length(), curLen = cur.length();
        if(preLen == 0 || curLen == 0)
            return preLen == 0? curLen : preLen;
        int[][] dp = new int[preLen + 1][curLen + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= preLen ; i ++) {
            dp[i][0] = i;
        }
        for(int j =1 ; j <= curLen ; j ++) {
            dp[0][j] = j;
        }
        for(int i = 1; i <= preLen; i++){
            for(int j = 1; j <= curLen; j++){
                if(pre.charAt(i-1) != cur.charAt(j-1))
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1],dp[i-1][j]) + 1);
            }
        }
        return dp[preLen][curLen];
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input = "";
        if (scan.hasNext()) {
            input = scan.nextLine();
        }
        scan.close();
        String[] array = input.split(",");
        System.out.println(minNum(array[0] , array[1]));
    }
}
