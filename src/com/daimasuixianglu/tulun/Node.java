package com.daimasuixianglu.tulun;

import java.util.ArrayList;

public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;//从我出去的边连向了哪些点
    public ArrayList<Edge> edges;//从我发散的边有哪些
    public Node(int value){
        this.value=value;
        in=0;
        out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }
}
class Edge{
    public int weight;
    public Node from;
    public Node to;
    public Edge(int weight,Node from,Node to){
        this.weight=weight;
        this.from=from;
        this.to=to;
    }
}
