package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一棵有n个节点的树，节点用1,2, …, n编号。1号节点为树的根节点，每个节点上有一个用大写字母表示的标记。求每个节点的子树中出现的字母标记种类数。
 * 注：子树的定义：设T是有根树，a是T中的一个顶点，由a以及a的所有后裔（后代）导出的子图称为有根树T的子树。
 * 输入描述
 * 第一行输入一个正整数n, 表示树的节点数量。
 * 第二行输入n-1个正整数，第i个整数表示第i+1号节点的父亲节点。
 * 第三行输入长度为n的由大写字母组成的字符串s1s2…sn，第i个字符si表示第i号节点的标记。
 * 3 ≤ n ≤ 50000。
 * 数据保证形成一棵合法的树，字符串由大写字母组成。
 * 输出描述
 * 输出n个整数，相邻两个数之间用空格隔开，第i个整数表示第i号节点的子树中出现不同的字母种类数。
 * 样例输入
 * 6
 * 1 2 2 1 4
 * ABCCAD
 * 样例输出
 * 4 3 1 2 1 1
 * 1号节点的子树有节点{1,2,3,4,5,6}，出现了A, B, C, D四种字母。
 * 2号节点的子树有节点{2,3,4,6}，出现了B, C, D三种字母。
 * 3号节点的子树有节点{3}，出现了C一种字母。
 * 4号节点的子树有节点{4, 6}， 出现了C，D两种字母。
 * 5号节点的子树有节点{5}，出现了A一种字母。
 * 6号节点的子树有节点{6}，出现了D一种字母。
 */
public class 字母树 {
    static int n;
    static char[] s;
    static int[] line2, ans;
    static ArrayList<Integer>[] child;
    static Set<Character>[] subtree_alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new char[n + 1];
        line2 = new int[n - 1];

        String[] line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            s[i + 1] = line[i].charAt(0);
        }

        line = br.readLine().split(" ");
        for (int i = 0; i < n - 1; i++) {
            line2[i] = Integer.parseInt(line[i]);
        }

        child = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            child[line2[i]].add(i + 2);
        }

        subtree_alpha = new HashSet[n + 1];
        ans = new int[n + 1];
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void dfs(int i) {
        Set<Character> alpha = new HashSet<>();
        alpha.add(s[i]);
        for (int node : child[i]) {
            dfs(node);
            alpha.addAll(subtree_alpha[node]);
        }
        subtree_alpha[i] = alpha;
        ans[i] = alpha.size();
    }
}

