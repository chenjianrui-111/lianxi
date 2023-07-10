package com.xiaochao.前缀和;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一个长度为 N 的数列，其中一段的连续子序列A1,A2,...,Aj(i <= j)之和是 k 的整数倍
 * 我们就称这个区间[i,j]是k的整数倍区间
 * 求出数列中共有多少个整数倍空间
 * 输入格式：
 * 第一行有两个正整数 N 和 K
 * 以下N行每行包含一个整数 Ai
 * 样例输入
 * 5 2
 * 18
 * 2
 * 3
 * 4
 * 5
 * 输出
 * 7
 */
public class K倍区间 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + sc.nextLong();
        long ans = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        for (int i = 1; i <= n; i++) {
            long u = s[i] % k;
            if (map.containsKey(u)) ans += map.get(u);
            map.put(u, map.getOrDefault(u, 0) + 1);
        }
        System.out.println(ans);
    }
}
