package com.cjr.paixu;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class qianKGeGaoPinYuanSu {
    public int[] topKFrequent(int[] nums, int k) {
        //使用字典统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int num:nums){
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        //遍历map，用最小堆保存频率最大的K个元素
        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });
        for (Integer key:map.keySet()){
            if (pq.size()<k){
                pq.add(key);
            }else if (map.get(key)<map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        //取出最小堆中的元素
        int ans[]=new int [k];
        int idx=0;
        while (!pq.isEmpty()){
            ans[idx++]=pq.peek();
        }
        return ans;
    }
}
