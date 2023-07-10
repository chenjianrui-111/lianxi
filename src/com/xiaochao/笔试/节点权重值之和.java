package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 给你一棵树，树上每个节点要么是红色要么是黑色，根节点为1。每个节点的权重值为根节点到该节点路径上红色节点个数
 * 和蓝色节点个数的差的绝对值。求所有节点权重值之和。
 * 输入数据第一行是节点个数
 * 第二行是每个节点的颜色 比如 RBR
 * 然后N-1行是边的关系 比如 1 2 这样
 * 思路
 * 建树，dfs模拟即可
 */
class ListNode1{
    long val;
    ListNode1 next;
    public ListNode1(long val,ListNode1 next){
        this.val =val;
        this.next = next;
    }

    public ListNode1() {

    }
}
public class 节点权重值之和 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int root = 0;
        int n = sc.nextInt();
        System.out.println(n);
        int color[] = new int[n];
        ListNode1[] graph = new ListNode1[n];
        for (int i = 0; i < n ; i++) {
            graph[i] = new ListNode1(0,null);
        }
        sc.nextLine();
        String str = sc.nextLine();
        System.out.println(str);
        for (int i = 0; i < n ; i++) {
            if (str.charAt(i) == 'R'){
                color[i] = 1;
            }
        }
        for (int i = 0; i < n - 1 ; i++) {
            int u =sc.nextInt() - 1;
            int v =sc.nextInt() - 1;
            insert(u,v,graph);
        }
        int[] visited =new int[n];
        dfs(root,visited,0,0,color,graph);
        long ans = 0;
        for (int i = 0; i <n ; i++) {
            ans += graph[i].val;
        }
        System.out.println(ans);
    }
    public static void insert(int u,int v,ListNode1[] graph){
        ListNode1 vNode = new ListNode1(v,graph[u].next);
        graph[u].next = vNode;
        ListNode1 uNode = new ListNode1(u,graph[v].next);
        graph[v].next = uNode;
    }

    public static void dfs(int root,int[] visited,int red,int blue,int color[],ListNode1[] G){
        visited[root] = 1;
        if(color[root]==1) red++;
        else blue++;
        G[root].val = Math.abs(red-blue);
        ListNode1 p = G[root].next;
        while(p!=null){
            if(visited[(int)p.val]!=1){
                dfs((int)p.val,visited,red,blue,color,G);
            }
            p = p.next;
        }
        visited[root] = 0;
    }
}
