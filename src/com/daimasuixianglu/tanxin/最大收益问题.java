package com.daimasuixianglu.tanxin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入： 正数数组costs， 正数数组profits ，正数k ，正数m
 * 含义： costs[i]表示i号项目的花费 ，profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)， k表示你只能串行的最多做k个项目 ，m表示你初始的资金
 *  说明： 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。 输出： 你最后获得的最大钱数。
 * 示例1
 * 输入
 * 1,0,[1,1,2],[1,2,3]
 * 输出
 * 0
 * 示例2
 * 输入
 * 2,0,[0,1,1],[1,2,3]
 * 输出
 * 4
 */
public class 最大收益问题 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param k       int整型 表示你只能串行的最多做k个项目
     * @param m       int整型 表示你初始的资金
     * @param costs   int整型一维数组 costs[i]表示i号项目的花费
     * @param profits int整型一维数组 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * @return int整型
     */
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }

    }

    public int findMaximizedCapital (int k, int m, int[] costs, int[] profits) {
        // write code here
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i<costs.length; i++) {
            Node curr = new Node(profits[i],costs[i]);
            minCostQ.add(curr);
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= m) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return m;
            }
            m += maxProfitQ.poll().p;
        }
        return m;
    }
}
