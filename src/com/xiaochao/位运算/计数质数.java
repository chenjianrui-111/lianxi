package com.xiaochao.位运算;

import java.util.Arrays;
//素数的定义看起来很简单，如果一个数如果只能被 1 和它本身整除，那么这个数就是素数。
//n/2 + n/3 + n/5 + n/7 + … = n × (1/2 + 1/3 + 1/5 + 1/7…)
//括号中是素数的倒数。其最终结果是 O(N * loglogN)，有兴趣的读者可以查一下该算法的时间复杂度证明
public class 计数质数 {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++)
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)
                    isPrime[j] = false;
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i]) count++;
        return count;
    }
}
