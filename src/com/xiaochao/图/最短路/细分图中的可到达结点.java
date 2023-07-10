package com.xiaochao.图.最短路;

import java.util.*;

/**
 * 给你一个无向图（原始图），图中有 n 个节点，编号从 0 到 n - 1 。你决定将图中的每条边 细分 为一条节点链，每条边之间的新节点数各不相同。
 * 图用由边组成的二维数组 edges 表示，其中 edges[i] = [ui, vi, cnti] 表示原始图中节点 ui 和 vi 之间存在一条边，cnti 是将边 细分 后的新节点总数。注意，cnti == 0 表示边不可细分。
 * 要 细分 边 [ui, vi] ，需要将其替换为 (cnti + 1) 条新边，和 cnti 个新节点。新节点为 x1, x2, ..., xcnti ，新边为 [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi] 。
 * 现在得到一个 新的细分图 ，请你计算从节点 0 出发，可以到达多少个节点？如果节点间距离是 maxMoves 或更少，则视为 可以到达 。
 * 给你原始图和 maxMoves ，返回 新的细分图中从节点 0 出发 可到达的节点数 。
 * 输入：edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * 输出：13
 * 解释：边的细分情况如上图所示。
 * 可以到达的节点已经用黄色标注出来。
 */
public class 细分图中的可到达结点 {
    public int countReachable(int[][] edges, int maxMoves, int n) {
        // 构建新的细分图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cnt = edge[2];
            List<Integer> nodes = new ArrayList<>();
            nodes.add(u);
            for (int i = 1; i <= cnt; i++) {
//                nodes.add(getNewNode(u, i));
                int[] newNode = getNewNode(u, i);
                nodes.add(newNode[0]);
                nodes.add(newNode[1]);
            }
            nodes.add(v);
            for (int i = 0; i < nodes.size()-1; i++) {
                int node = nodes.get(i), next_node = nodes.get(i+1);
                if (!graph.containsKey(node)) {
                    graph.put(node, new ArrayList<>());
                }
                if (!graph.containsKey(next_node)) {
                    graph.put(next_node, new ArrayList<>());
                }
                graph.get(node).add(next_node);
                graph.get(next_node).add(node);
            }
        }

        // BFS 遍历
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], depth = curr[1];
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            if (depth == maxMoves) {
                break;
            }
            for (int next_node : graph.getOrDefault(node, new ArrayList<>())) {
                queue.offer(new int[]{next_node, depth+1});
            }
        }

        return visited.size();
    }

    private int[] getNewNode(int node, int idx) {
        return new int[]{node,idx};
    }
}
class Node {
    private int id;
    private int weight;

    public Node(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
