package com.daimasuixianglu.tanxin;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
        for (int i = 0; i <arr.length ; i++) {
            pq.add(arr[i]);
        }
        int sum=0;
        int cur=0;
        while (pq.size()>1){//小根堆中剩一个数停止
            cur=pq.poll()+pq.poll();
            sum+=cur;
            pq.add(cur);
        }
        return sum;
    }
    public static class MinheapComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
