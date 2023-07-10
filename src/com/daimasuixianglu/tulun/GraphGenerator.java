package com.daimasuixianglu.tulun;

public class GraphGenerator {
    //matrix所有的边
    //N*3的矩阵
    //[weight,from节点上面的值，to节点上面的值]
    public static Graph creatGraph(Integer[][] matrix){
        Graph graph=new Graph();
        for (int i = 0; i <matrix.length ; i++) {//matrix[0][0] matrix[0][1] matrix[1][2]
            Integer from=matrix[i][1];
            Integer to=matrix[i][2];
            Integer weight=matrix[i][0];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            Node fromNode=graph.nodes.get(from);
            Node toNode=graph.nodes.get(to);

            Edge newEdge=new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
