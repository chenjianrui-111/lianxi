package com.xiaochao.位运算;

/**
 * n & (n-1) 这个操作是算法中常⻅的，作⽤是消除数字 n 的⼆进制表示中的最后⼀个 1
 * 相同为 1 ，不同为 0
 */
public class 位1的个数 {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
