package com.xiaochao.数组链表.查分数组;

/**
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，
 * 接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 */
public class 拼车 {
    public boolean carPooling(int[][] trips, int capacity) {
        //最多有1001个车站
        int[] nums =new int[1001];
        //构造差分解法
        Difference df=new Difference(nums);
        for (int[] trip:trips){
            // 第 trip[1] 站乘客上车
            int i =trip[1];
            // 第 trip[2] 站乘客已经下车，
            // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
            int j =trip[2] - 1;
            // 乘客数量
            int val=trip[0];
            // 进行区间操作
            df.increment(i, j, val);
        }
        int []res =df.result();
        //乘客自始至终都不应该超数
        for (int i = 0; i <res.length ; i++) {
            if (capacity < res[i]){
                return false;
            }
        }
        return true;
    }
}
