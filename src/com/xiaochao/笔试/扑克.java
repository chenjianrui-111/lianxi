package com.xiaochao.笔试;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * Alice和Bob在玩一个游戏。有n张卡牌，点数分别为1到n。进行洗牌后，n张牌从上到下叠放形成一个牌堆。
 * 每次Alice先将当前牌堆顶的一张牌放到牌堆底，然后Bob再将当前牌堆顶的一张牌放到牌堆底。
 * （特别地，当牌堆中只有一张牌时，相当于不进行任何操作）接着，他们会翻开当前牌堆顶的牌，并记下它的点数。当所有牌都被翻开后，
 * 他们也记下了n个点数。现在他们想根据记下的这个序列来还原一开始的牌（从牌堆顶到牌堆底每一张牌的点数）。
 * 输入描述
 * 第一行是一个正整数n，表示有n张牌。
 * 接下来一行n个用空格隔开的正整数，第i个数a_i表示第i张被翻开的牌的点数。
 * 1<=n<=100000
 * 输出描述
 * 一行n个用空格隔开的正整数，第i个数表示初始牌堆中从牌堆顶到牌堆底的第i张牌的点数。
 * 样例输入
 * 4
 * 1 2 3 4
 * 样例输出
 * 4 2 1 3
 * 提示
 * 样例解释1
 * 初始牌堆为：4 2 1 3
 * 1. Alice和Bob分别操作后牌堆为：1 3 4 2，此时1被翻开，牌堆变为3 4 2
 * 2. Alice和Bob分别操作后牌堆为：2 3 4，此时2被翻开，牌堆变为3 4
 * 3. Alice和Bob分别操作后牌堆为：3 4，此时3被翻开，牌堆变为4
 * 4.Alice和Bob分别操作后牌堆依旧为4，此时4被翻开。
 */
public class 扑克 {

    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int n =  Integer.parseInt(cin.nextLine());
        int[] card = Arrays.stream(cin.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int[] origin = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) d.offerLast(n - i - 1);
        for (int i = 0; i < n; i++) {
            d.offerFirst(d.pollLast());
            d.offerFirst(d.pollLast());
            origin[d.pollLast()] = card[i];
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", origin[i]);
        }
    }
}
