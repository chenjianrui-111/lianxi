package com.daimasuixianglu.tulun;

import java.util.*;
//要求无向图
public class Code04_KruskaL {

 public static class EdgeComparator implements Comparator<Edge>{
     @Override
     public int compare(Edge o1, Edge o2) {
         return o1.weight -o2.weight;
     }
 }

 public static class MySets{
    //某个描述多个小集合的结构
     public HashMap<Node,List<Node>> setMap;

     public MySets(List<Node> nodes){
         for (Node cur:nodes){
             //MySets内部把每一个点放到,单独的小集合里去
             List<Node> list=new ArrayList<>();
             list.add(cur);
             setMap.put(cur,list);
         }
     }
     //a点和b点是否属于一个集合
     public boolean isSameSet(Node from,Node to){
         List<Node> fromSet=setMap.get(from);
         List<Node> toSet=setMap.get(to);
         return fromSet==toSet;
     }
     //把a所在集合所有的点，和b所在集合的所有的点，合并成一个大集合
     public void union(Node from,Node to){
         List<Node> fromSet=setMap.get(from);
         List<Node> toSet=setMap.get(to);
         for (Node toNode:toSet){
             fromSet.add(toNode);
             setMap.put(toNode,fromSet);
         }
     }
  }
  public static Set<Edge> KrukalMST(Graph graph){
     MySets mySets=new MySets((List<Node>) graph.nodes.values());
      PriorityQueue<Edge> priorityQueue=new PriorityQueue<>(new EdgeComparator());
      for (Edge edge:graph.edges){//M条边
          priorityQueue.add(edge);//O(logM)
      }
      Set<Edge> result=new HashSet<>();
      while (!priorityQueue.isEmpty()){//M条边
          Edge edge=priorityQueue.poll();//O(logM)
          if (!mySets.isSameSet(edge.from,edge.to)){//O(1)
              result.add(edge);
              mySets.union(edge.from,edge.to);
          }
      }
      return result;
  }

}
