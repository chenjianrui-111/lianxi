package com.daimasuixianglu.huisu.pailiewenti;

import java.util.*;

/**
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 */
public class 重新安排行程 {
    public List<String> findItinerary(List<List<String>> tickets) {
        //因为逆序插入，所以用链表
        List<String> ans = new LinkedList<>();
        if (tickets == null || tickets.size() == 0) {
            return ans;
        }
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            //因为涉及删除操作，我们用链表
            List<String> nbr = graph.computeIfAbsent(pair.get(0), k -> new LinkedList<>());
            nbr.add(pair.get(1));
        }
        //按目的顶点排序
        graph.values().forEach(x -> x.sort(String::compareTo));
        visit(graph, "JDK", ans);
        return ans;
    }

    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private void visit(Map<String, List<String>> graph, String src, List<String> ans) {
        List<String> nbr = graph.get(src);
        while (nbr != null && nbr.size() > 0) {
            String dest = nbr.remove(0);
            visit(graph, dest, ans);
        }
        ans.add(0, src); // 逆序插入
    }
}
    class Solution {
        private Deque<String> res;
        //map<出发机场, map<到达机场, 航班次数>>
        private Map<String, Map<String, Integer>> map;

        private boolean backTracking(int ticketNum){
            if(res.size() == ticketNum + 1){
                return true;
            }
            String last = res.getLast();
            if(map.containsKey(last)){//防止出现null
                for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                    int count = target.getValue();
                    if(count > 0){
                        res.add(target.getKey());
                        target.setValue(count - 1);
                        if(backTracking(ticketNum)) return true;
                        res.removeLast();
                        target.setValue(count);
                    }
                }
            }
            return false;
        }

        public List<String> findItinerary(List<List<String>> tickets) {
            map = new HashMap<String, Map<String, Integer>>();
            res = new LinkedList<>();
            for(List<String> t : tickets){
                Map<String, Integer> temp;
                if(map.containsKey(t.get(0))){
                    temp = map.get(t.get(0));
                    temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
                }else{
                    temp = new TreeMap<>();//升序Map
                    temp.put(t.get(1), 1);
                }
                map.put(t.get(0), temp);

            }
            res.add("JFK");
            backTracking(tickets.size());
            return new ArrayList<>(res);
        }
    }
