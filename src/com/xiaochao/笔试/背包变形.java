package com.xiaochao.笔试;

import java.util.PriorityQueue;

public class 背包变形 {
    public static int maxNumberOfBackpacks(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (capacity[a] - rocks[a]) - (capacity[b] - rocks[b]));

        for (int i = 0; i < capacity.length; i++) {
            minHeap.add(i);
        }

        while (!minHeap.isEmpty() && additionalRocks > 0) {
            int index = minHeap.poll();
            int availableSpace = capacity[index] - rocks[index];

            if (availableSpace <= additionalRocks) {
                rocks[index] += availableSpace;
                additionalRocks -= availableSpace;
            } else {
                rocks[index] += additionalRocks;
                additionalRocks = 0;
            }
        }

        int fullBackpacks = 0;
        for (int i = 0; i < capacity.length; i++) {
            if (rocks[i] == capacity[i]) {
                fullBackpacks++;
            }
        }

        return fullBackpacks;
    }

//    public static void main(String[] args) {
//        int[] capacity = {2, 3, 4, 5};
//        int[] rocks = {1, 2, 4, 4};
//        int additionalRocks = 2;
//
//        System.out.println( maxNumberOfBackpacks(capacity, rocks, additionalRocks));
//    }

}
