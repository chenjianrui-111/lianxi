package com.xiaochao.图.拓扑排序;
import java.io.*;
import java.util.*;
public class 模板 {

        /**
         * 拓扑序
         */
        static List<Integer> ans = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            String[] line = reader.readLine().split(" ");
            // 存储点的出度集合
            List<List<Integer>> graph = new ArrayList<>();
            // 点数
            int n = Integer.parseInt(line[0]);
            // 边数
            int m = Integer.parseInt(line[1]);
            // 存储点的入度数
            int[] inDegree = new int[n];
            // 初始化点
            for (int i = 0; i < n; i++) {
                // 此处由于点的出度可能有多个,则使用list初始化
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                line = reader.readLine().split(" ");
                // 边的起始点
                int u = Integer.parseInt(line[0]);
                // 边的结束点
                int v = Integer.parseInt(line[1]);
                // v点入度+1
                inDegree[v - 1]++;
                // u点增加出度,指向v点
                graph.get(u - 1).add(v - 1);
            }
            boolean flag = topologicalSort(graph, inDegree);
            if (flag) {
                // 是有向无环图,输出拓扑排序
                for (int i = 0; i < ans.size(); i++) {
                    writer.write(String.valueOf(ans.get(i)));
                    if (i != n - 1) {
                        writer.write(" ");
                    }
                }
            } else {
                writer.write("-1");

            }
            writer.flush();
            writer.close();

        }

        public static boolean topologicalSort(List<List<Integer>> graph, int[] inDegree) {
            int num = 0;
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < inDegree.length; i++) {
                // 先找入度为0的点,放入队列
                if (inDegree[i] == 0) {
                    deque.offer(i);

                }
            }
            while (!deque.isEmpty()) {
                int u = deque.poll();
                // 入度为0的点为起始点
                ans.add(u + 1);
                for (int i = 0; i < graph.get(u).size(); i++) {
                    // 获取入度为0的点的出度点
                    int v = graph.get(u).get(i);
                    // 这个点的入度减1
                    inDegree[v]--;
                    // 寻找下一个入度为0的点
                    if (inDegree[v] == 0) {
                        deque.offer(v);
                    }
                }
                graph.get(u).clear();
                num++;
            }
            // 若此时输出的结点数不等于有向图中的顶点数，则说明有向图中存在回路，否则输出的顶点的顺序即为一个拓扑序列
            return num == inDegree.length;
        }
}
