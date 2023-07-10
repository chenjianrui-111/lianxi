package com.daimasuixianglu.tulun;

/**
 * 在一个有 n 个点， m 个边的有向图中，已知每条边长，求出 1 到 n 的最短路径，返回 1 到 n 的最短路径值。如果 1 无法到 n ，输出 -1
 * 图中可能有重边，无自环。
 * 数据范围：1<n<=500 ，1<=m<=5000  ，1<=dist(n,m)<=1000
 * 示例1
 * 输入
 * 5,5,[[1,2,2],[1,4,5],[2,3,3],[3,5,4],[4,5,5]]
 * 输出
 * 9
 * 示例2
 * 输入
 * 2,1,[[1,2,4]]
 * 输出
 * 4
 * 备注:
 * 两个整数n和m,表示图的顶点数和边数。
 * 一个二维数组，一维3个数据，表示顶点到另外一个顶点的边长度是多少
 * 每条边的长度范围[0,1000]。
 * 注意数据中可能有重边
 */
//public class 单元最短路径 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int 顶点数
     * @param m int 边数
     * @param graph int二维数组 一维3个数据，表示顶点到另外一个顶点的边长度是多少​
     * @return int
     */

//    public int findShortestPath (int n, int m, int[][] graph) {
//        // write code here
//        // 最短路径 迪杰斯特拉
//        int[] dis = new int[n];
//        int[] book = new int[n];
//        int[][] e = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) {
//                    e[i][j] = 0;
//                } else {
//                    e[i][j] = 0x0fffffff;
//                }
//            }
//        }
//
//    }
//}
