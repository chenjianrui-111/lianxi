package com.daimasuixianglu.tanxin;

import java.util.Arrays;

/**
 *有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 */
public class 用最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        if (points.length==0) return 0;
        Arrays.sort(points,(a,b)-> Integer.compare(a[0], b[0]));   //先按照第一位排序
        int count=1;//弓箭数
        for (int i = 1; i <points.length ; i++) {
            if (points[i][0] > points[i-1][1]){//如果前一位的右边界小于后一位的左边界，则一定不重合
                count++;
            }else {
                points[i][1]=Math.min(points[i][1],points[i-1][1]);// 更新重叠气球最小右边界,覆盖该位置的值，留到下一步使用
            }
        }
        return count;
    }
}
