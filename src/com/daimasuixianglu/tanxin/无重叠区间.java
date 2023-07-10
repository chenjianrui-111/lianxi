package com.daimasuixianglu.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠
 */
public class 无重叠区间 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length<2) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
               if (o1[1] !=o2[1]){
                   return Integer.compare(o1[1],o2[1]);
               }else {
                   return Integer.compare(o1[0],o2[0]);
               }
            }
        });
        int count=1;//记录非交叉区间的个数
        int edge=intervals[0][1];// 记录区间分割点
        for (int i = 1; i <intervals.length ; i++) {
            if (edge<= intervals[i][0]){
                count++;
                edge=intervals[i][1];
            }
        }
        return intervals.length-count;
    }
}
class Solution05{
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length<2) return 0;
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0], b[0]));   //先按照第一位排序
        int count=1;//弓箭数
        for (int i = 1; i <intervals.length ; i++) {
            if (intervals[i][0] >=intervals[i-1][1]){//如果前一位的右边界小于等于后一位的左边界，则一定不重合
                count++;
            }else {
                intervals[i][1]=Math.min(intervals[i][1],intervals[i-1][1]);// 更新重叠气球最小右边界,覆盖该位置的值，留到下一步使用
            }
        }
        return intervals.length-count;
    }
}
