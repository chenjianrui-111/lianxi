package com.daimasuixianglu.shuzu;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
 * 返回这 两个区间列表的交集 。
 * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 */
public class 区间列表的交集 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res =new LinkedList<>();
        int i=0,j=0;
        while (i < firstList.length && j <secondList.length){
            int a1 =firstList[i][0],a2=firstList[i][1];
            int b1=secondList[i][0],b2=secondList[i][1];

            if (b2 >= a1 && a2 >= b1){
                res.add(new int[]{
                        Math.max(a1,b1),Math.min(a2,b2)
                });
            }
            if (b2 < a2){
                j++;
            }else {
                i++;
            }
        }
        return res.toArray(new int[0][0]);
    }
}
