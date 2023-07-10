package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 小红希望你构造一个长度为 n 的01串，其中恰好有k个1，且恰好有t对相邻的字符都是1.
 * 三个正整数n,k,t 用空格隔开
 * 1<=n<=10^5
 * 0<=k,t<=n
 * 输出描述：
 * 如果无法完成构造，请输出-1.否则输出任意一个满足条件的01串即可
 * 示例1
 * 3 2 1
 * 110
 */
public class 小红的01串构造 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k =sc.nextInt();
        int t =sc.nextInt();
        if (k-t-1<0){
            System.out.println(-1);
        }
        else if (t+1+2*(k-t-1) > n){
            System.out.println(-1);
        }
        else{
        }
    }
}
/**
 *n, k, t = [int(x) for x in input().split()]
 * if k - t - 1 < 0 or t + 1 + 2 * (k - t - 1) > n:
 *     print(-1)
 * else:
 *     print(('1' * (t + 1) + '01' * (k - t - 1)).ljust(n, '0'))
 */
