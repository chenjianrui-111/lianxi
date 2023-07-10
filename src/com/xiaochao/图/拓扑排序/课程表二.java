package com.xiaochao.图.拓扑排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 */
public class 课程表二 {

    //记录后序遍历结果
    List<Integer> postorder =new ArrayList<>();
    //记录一次递堆栈中的节点
    boolean[] onPath;
    //记录当前节点是否遍历过
    boolean[] visited;
    //判断是否成环
    boolean hasCycle;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph =buildGraph(numCourses,prerequisites);
        visited = new boolean[numCourses];
        onPath =new boolean[numCourses];
        //遍历图
        for (int i = 0; i < numCourses ; i++) {
            traverse(graph,i);
        }
        //有环图无法遍历
        if (hasCycle){
            return new int[]{};
        }
        //逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses ; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }
    //图遍历函数
    void traverse(List<Integer>[] graph , int s){
        if (onPath[s]){
            //发现环
            hasCycle = true;
        }
        if (visited[s] ||hasCycle){
            return;
        }
        //前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t :graph[s]){
            traverse(graph, t);
        }
        //后序遍历位置
        postorder.add(s);
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i <numCourses ; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites){
            int from = edge[1], to = edge[0];
            // 添加⼀条从 from 指向 to 的有向边
            // 边的⽅向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }

}
