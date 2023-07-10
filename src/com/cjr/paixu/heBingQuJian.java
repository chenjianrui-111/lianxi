package com.cjr.paixu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class heBingQuJian {
    public int[][] merge(int[][] intervals) {
        // 1. 按照区间左边的值进行升序排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        // 2. 初始化 outputs, 用于存储合并之后区间结果的动态数组
        ArrayList<int[]> outputs = new ArrayList<>();
        // 3. 遍历处理每一个区间
        for (int i = 0; i <intervals.length ; i++) {
            int[] currentVals=intervals[i];
            if (outputs.isEmpty()){
                outputs.add(currentVals);
            }else {
                //判断是否有重叠
                //拿到outputs最后一个区间
                int[] outputsLastInterval=outputs.get(outputs.size()-1);
                int outputLastRight=outputsLastInterval[1];
                int currLeft=currentVals[0];
                if (outputLastRight<currLeft){
                    //没有重叠区
                    outputs.add(currentVals);
                }else {//重叠合并
                    int currRight=currentVals[1];
                    outputsLastInterval[1]=Math.max(outputLastRight,currRight);
                }
            }
        }
        return outputs.toArray(new int[outputs.size()][]);
    }
}
