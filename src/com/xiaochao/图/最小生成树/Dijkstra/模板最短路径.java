package com.xiaochao.图.最小生成树.Dijkstra;

import java.util.Arrays;
import java.util.Scanner;

public class 模板最短路径 {
    private static int[] path;//存储到达终点前的一个点
    private static int[] path_len;//存储1号点到终点的最短路径长度
    public static final int MaxValue = 50000*10000 + 1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        path = new int[5001];
        path_len = new int[5001];
        //List<int[]> list = new ArrayList<>();//存储图中边和对应权值
        int[][] graph = new int[5001][5001];
        for(int[] ints:graph){
            Arrays.fill(ints,MaxValue);
        }
        for(int i=0;i<m;i++){
            String[] input = sc.nextLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int len = Integer.parseInt(input[2]);
            graph[u][v] = len;
            graph[v][u] = len;
        }
        shortestPath_Dijkstra(1,5000,graph);
        if(path_len[n]==MaxValue)
            System.out.println(-1);
        else
            System.out.println(path_len[n]);

    }
    //u为起始点，n是顶点个数，grap是存储的图信息。
    public static void shortestPath_Dijkstra(int u,int n,int[][] graph){
        boolean[] flag = new boolean[n+1];//表示是否确定好最短路径
        int min=0,k=0;
        for(int i=1;i<=n;i++){
            flag[i] = false;
            path_len[i]=graph[u][i];
            path[i] = 1;
        }
        flag[u] = true;
        path[u] = 1;
        path_len[u] = 0;
        for(int i=1;i<n;i++){
            min = MaxValue;
            for(int j=1;j<=n;j++){
                if(!flag[j]&&(path_len[j]<min)){
                    min = path_len[j];
                    k = j;
                }
            }
            flag[k] = true;
            for(int j=1;j<=n;j++){
                if(!flag[j]&&(min+graph[k][j]<path_len[j])){
                    path_len[j]=min+graph[k][j];
                    path[j] = k;
                }
            }
        }

    }
}
