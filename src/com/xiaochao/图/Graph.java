package com.xiaochao.图;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组 boolean[],记录某个顶点是否被访问
    private boolean[] isVisited ;

    public Graph(int n){
        //初始矩阵
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }
    //得到第一个邻接节点的下标
    //如果存在就返回对应的下标，否则返回-1
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size() ; j++) {
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标，获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    private void dfs(boolean[] isVisited,int i){
        //首先我们访问该节点
        System.out.println(getValueByIndex(i));
        isVisited[i] = true;
        //查找节点 i 的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果 w 节点已经被访问过
            else {
                w = getNextNeighbor(i,w);
            }
        }

    }
    //对 dfs 进行重载，遍历我们所有的节点，并进行dfs
    public void dfs(){
        for (int i = 0; i < getNumOfVertex() ; i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }
    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点 i 对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回 v1 或 v2 的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
