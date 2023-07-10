package com.xiaochao.DP.数位dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 有向图 ，它含有 n 个节点和 m 条边。节点编号从 0 到 n - 1 。
 * 给你一个字符串 colors ，其中 colors[i] 是小写英文字母，表示图中第 i 个节点的 颜色 （下标从 0 开始）。同时给你一个二维数组 edges ，其中 edges[j] = [aj, bj] 表示从节点 aj 到节点 bj 有一条 有向边 。
 * 图中一条有效 路径 是一个点序列 x1 -> x2 -> x3 -> ... -> xk ，对于所有 1 <= i < k ，从 xi 到 xi+1 在图中有一条有向边。路径的 颜色值 是路径中 出现次数最多 颜色的节点数目。
 * 请你返回给定图中有效路径里面的 最大颜色值 。如果图中含有环，请返回 -1 。
 * 输入：colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * 输出：3
 * 解释：路径 0 -> 2 -> 3 -> 4 含有 3 个颜色为 "a" 的节点（上图中的红色节点）。
 */
public class 有向图中最大颜色值 {
    char[] color;//每个点的颜色
    int ret=0;//记录颜色最大值
    int[][] dp;//dp
    public int largestPathValue(String colors, int[][] edges) {
        List<List<Integer>> lj=new ArrayList<>();//邻接表
        int[] flags=new int[colors.length()];//记录访问标志 0未访问 1正在访问 -1访问结束 用于判断环
        color=colors.toCharArray();
        dp=new int[colors.length()][26];//记录以这个点为起点，所有有效路径中，各颜色的最大值
        //构建邻接表
        for(int i=0;i<colors.length();i++){
            lj.add(new ArrayList<Integer>());
        }
        for(int i=0;i<edges.length;i++){
            lj.get(edges[i][0]).add(edges[i][1]);
        }
        //对每个点dfs false代表成环 直接返回-1
        for(int i=0;i<colors.length();i++){
            if(!dfs(lj,flags,i)){
                return -1;
            }
        }
        //无环 返回记录的最大值
        return ret;
    }
    private boolean dfs(List<List<Integer>> lj,int[] flags,int i){
        if(flags[i]==-1){//访问过
            return true;
        }
        if(flags[i]==1){//成环
            return false;
        }
        flags[i]=1;//正在访问
        for(Integer j:lj.get(i)){//遍历
            if(!dfs(lj,flags,j)){
                return false;
            }
            for(int k=0;k<26;k++){//继承各边的颜色最大值
                dp[i][k]=Math.max(dp[i][k],dp[j][k]);
            }
        }
        flags[i]=-1;//访问结束
        dp[i][color[i]-'a']+=1;//加上该点的颜色
        ret=Math.max(dp[i][color[i]-'a'],ret);//记录变化的最大值
        return true;
    }
}
