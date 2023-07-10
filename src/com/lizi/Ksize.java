package com.lizi;

/**
 * 一个字符串形如aabb(长度为4,n变量)，连续段有2(k变量)，每个连续段的长度为2(长度大于等于2表示合法),如abb的话连续段也为2 但是第一个连续段长度小于2所以不合法。求给一个n,一个k，返回生成合法字符串的数量。答案需要对1000000取模。
 * 需要注意的是选择的字符串可以是任意一个小写字母
 * 样例：
 * n k
 * 4 1
 * 26
 */
public class Ksize {
    private long[][] record;
    private final long mod = (long)1e6;

    public int numsOfStrings(int n, int k) {
        this.record = new long[n + 1][k + 1];
        for(int i = 0;i <= n;i++) {
            for(int j = 0;j <= k;j++) {
                record[i][j] = -1;
            }
        }
        return (int)dfs(n, k);
    }

    private long dfs(int n, int k) {
        if(k > (n >> 1)) {
            return 0;
        }
        if(record[n][k] != -1) {
            return record[n][k];
        }
        if(k == 1) {
            if(n < 2) {
                return 0;
            } else {
                record[n][k] = 26;
                return 26;
            }
        }

        record[n][k] = 0;
        for(int i = 2;i <= n - 2;i++) {
            record[n][k] += 25 * dfs(i, k - 1);
            record[n][k] %= mod;
        }
        return record[n][k];
    }
}
