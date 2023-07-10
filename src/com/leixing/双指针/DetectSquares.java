package com.leixing.双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
 *
 * 添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 * 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
 *
 * 实现 DetectSquares 类：
 * DetectSquares() 使用空数据结构初始化对象
 * void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 * int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 *
 * 对于 add 操作，我们可以使用「哈希表 套 哈希表」的方式，以 {x, {y : 点 (x,y) 数量}} 的形式对传入点进行存储。
 * 对于 count 查询而言，假定传入的点为 (x, y)，我们可以先查询 x 行都有哪些列，枚举这些列 即枚举点 (x,ny)，
 * 由 y 和 ny 可得正方形边长 len，此时再检查唯一确定的两点 (x±len,y) 和 (x±len,ny) 的出现次数，
 * 应用乘法原理，即可知道该正方形的方案数，统计所有合法方案数即是该询问的答案。
 * 利用题目范围给定的 x 和 y 具有明确的范围 0 <= x, y <= 1000，我们可以使用数组充当哈希表，但是为了拓展性和减少边界判断，
 * 即支持将平面拓展到任意大小，最好还是直接使用哈希表
 */
 class DetectSquares {

    Map<Integer,Map<Integer,Integer>> row2Col=new HashMap<>();

    public void add(int[] point) {

       int x=point[0],y=point[1];
       Map<Integer,Integer> col2Cnt=row2Col.getOrDefault(x,new HashMap<>());
       col2Cnt.put(y,col2Cnt.getOrDefault(y,0)+1);
       row2Col.put(x,col2Cnt);
    }

    public int count(int[] point) {

       int x=point[0],y=point[1];
       int ans=0;
       Map<Integer,Integer> col2Cnt=row2Col.getOrDefault(x,new HashMap<>());
       for (int ny: col2Cnt.keySet()){
          if (ny == y) continue;
          int c1=col2Cnt.get(ny);
          int len=y - ny;
          int [] nums=new int[]{x+len,x-len};
          for (int nx:nums){
             Map<Integer,Integer> temp=row2Col.getOrDefault(nx,new HashMap<>());
             int c2=temp.getOrDefault(y,0);
             int c3=temp.getOrDefault(ny,0);
             ans=ans+c1* c2 * c3;
          }
       }
       return ans;
    }
}
