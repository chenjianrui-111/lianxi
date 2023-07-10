package com.daimasuixianglu.tulun;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code06_Dijkstra {
    public static HashMap<Node,Integer> dijkstra1(Node from){
        //此时从from出发到所有点的最小距离
        //key:从from出发到达所有的key
        //value:从from出发到达key的最小距离
        //如果在表中，没有T的记录，含义从from出发到T这个点的距离为正无穷
        HashMap<Node,Integer> distanceMap=new HashMap<>();
        distanceMap.put(from,0);
        //已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes=new HashSet<>();
        Node minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        while (minNode !=null){
            //此时选的记录从from出发...>minNode(distance)
            int distance=distanceMap.get(minNode);
            for (Edge edge:minNode.edges){
                Node toNode=edge.to;
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance+edge.weight);
                }else {
                    distanceMap.put(edge.to,Math.min(distanceMap.get(toNode),distance+edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        }
        return distanceMap;
    }
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node,Integer> distanceMap,HashSet<Node> selectedNodes){
        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> entry:distanceMap.entrySet()){
            Node node=entry.getKey();
            int distance=entry.getValue();
            if (!selectedNodes.contains(node) && distance<minDistance){
                minNode=node;
                minDistance=distance;
            }
        }
        return minNode;
    }
}
