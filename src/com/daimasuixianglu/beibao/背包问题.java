package com.daimasuixianglu.beibao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 有N件物品和一个容量为V的背包。第i件物品的价值是C[i]，重量是W[i]。求解将哪些物品装入背包可使价值总和最大。
 * 输入描述:
 * 输入第一行数 N V (1 <=N <=500) (1<= V <= 10000)
 * 输入 N行 两个数字 代表 C W (1 <= C <= 50000, 1 <= W <=10000)
 * 输出描述:
 * 输出最大价值
 * 示例1
 * 输入
 * 5 10
 * 8 6
 * 10 4
 * 4 2
 * 5 4
 * 5 3
 * 输出
 * 19
 * 示例2
 * 输入
 * 1 1
 * 10 2
 * 输出
 * 0
 */
public class 背包问题 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int v = Integer.parseInt(str[1]);
        int[] C = new int[n];
        int[] W = new int[n];
        for(int i=0;i<n;++i){
            str = br.readLine().split(" ");
            C[i] = Integer.parseInt(str[0]);
            W[i] = Integer.parseInt(str[1]);
        }
        int res=process(W,C,0,0,v);
        System.out.println(res);
    }
    public static int process(int[]W,int[]C,int index,int alreadyW,int V){
        if (alreadyW > V){
            return -1;
        }
        //重量没超
        if (index == W.length){
            return 0;
        }
        //不要当前货物，获得的最大价值
        int p1=process(W, C, index + 1, alreadyW, V);
        //要当前的货物，后续获得的最大价值
        //要当前货物，当前货物的价值+后续得到的价值
        int p2next=process(W, C, index + 1, alreadyW+W[index], V);
        int p2=-1;
        if (p2next != -1){
            p2=C[index] + p2next;
        }
        return Math.max(p1,p2);
    }
}
