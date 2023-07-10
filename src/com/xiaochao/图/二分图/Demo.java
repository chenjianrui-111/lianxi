package com.xiaochao.图.二分图;

import com.cjr.shu.Node;
import com.cjr.shu.TreeNode;


public class Demo {
    /* ⼆叉树遍历框架 */
    void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        traverse(root.right);
    }
    /* 多叉树遍历框架 */
    void traverse(Node root) {
        if (root == null) return;
        for (Node child : root.children)
            traverse(child);
    }
    private static class Graph{
        int id;
        Graph[] neighbors;

        public int[] neighbors(int s) {
            return null;
        }
    }
    /* 图遍历框架 */
    boolean[] visited;
    void traverse(Graph graph, int v) {
        // 防⽌⾛回头路进⼊死循环
        if (visited[v]) return;
        // 前序遍历位置，标记节点 v 已访问
        visited[v] = true;
        for (int neighbor : graph.neighbors(v))
            traverse(graph, neighbor);
    }

    /* 图遍历框架 */
    boolean[] visited1;
    void traverse1(Graph graph, int v) {
        // 前序遍历位置，标记节点 v 已访问
        visited[v] = true;
        for (int neighbor : graph.neighbors(v)) {
            if (!visited[neighbor]) {
                // 只遍历没标记过的相邻节点
                traverse1(graph, neighbor);
            }
        }
    }
    /* 二分图遍历框架 */
    void traverse(Graph graph, boolean[] visited, int v) {
        visited[v] = true;
        // 遍历节点 v 的所有相邻节点 neighbor
        for (int neighbor : graph.neighbors(v)) {
            if (!visited[neighbor]) {
                // 相邻节点 neighbor 没有被访问过
                // 那么应该给节点 neighbor 涂上和节点 v 不同的颜⾊
                traverse(graph, visited, neighbor);
            } else {
                // 相邻节点 neighbor 已经被访问过
                // 那么应该⽐较节点 neighbor 和节点 v 的颜⾊
                // 若相同，则此图不是⼆分图
            }
        }
    }
}
