package com.xiaochao.图.拓扑排序;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * floyed 算法
 * 判断从i到j 是否有路径。
 * 判断从i 途径 mid 到j是否有路径。
 *
 * 查询从任意的i 到任意的j 是否有路径。
 */
public class 课程表四 {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {

          /*
               floyed[i][j]
               if there is path from i to j

          */

        boolean[][] floyed = new boolean[numCourses][numCourses];

        for(int [] pre : prerequisites){
            floyed[pre[0]][pre[1]] = true;
        }

        for(int mid = 0; mid < numCourses; mid++){

            for(int i = 0; i < numCourses; i++){

                for(int j = 0; j < numCourses; j++){

                    floyed[i][j] = floyed[i][j]|| (floyed[i][mid] && floyed[mid][j]);
                }
            }
        }

        List<Boolean> result = new ArrayList<>();

        for(int[] query : queries){

            int i = query[0];
            int j = query[1];

            result.add(floyed[i][j]);
        }
        return result;

    }

}

/**
 * 这是一个有向无环图，先通过记忆化递归把某一门课程的前置课程全部记录下来，然后再循环queries去判断
 */
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //有向无环图的逆邻接表
        LinkedList<Integer>[] inverseAdj = new LinkedList[numCourses];
        //每门课程的前置课程集合
        HashSet<Integer>[] map = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            inverseAdj[i] = new LinkedList<>();
            map[i] = new HashSet<>();
        }
        for (int[] prerequisite : prerequisites) {
            inverseAdj[prerequisite[1]].add(prerequisite[0]);
        }
        boolean[] visited = new boolean[numCourses];
        //递归查找每门课程的所有前置课程
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs(i, inverseAdj, visited, map);
            }
        }
        List<Boolean> res = new ArrayList<>();
        //查找queries的前置关系
        for (int[] query : queries) {
            int from = query[0];
            int to = query[1];
            res.add(map[to].contains(from));
        }
        return res;
    }

    private static void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited, HashSet<Integer>[] map) {
        visited[vertex] = true;
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (!visited[w]) {
                //如果之前没访问过 则递归查找w的所有前置课程
                dfs(w, inverseAdj, visited, map);
            }
            //将w和w的所有前置课程加入vertex的前置课程集合中
            map[vertex].add(w);
            map[vertex].addAll(map[w]);
        }
    }
}

