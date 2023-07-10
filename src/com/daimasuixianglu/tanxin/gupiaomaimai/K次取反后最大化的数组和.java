package com.daimasuixianglu.tanxin.gupiaomaimai;

import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 思路
 * 贪心 + 分情况讨论 + 模拟
 * 假设取反前的总和为 sum，取反一个任意值 xx 后，对 sum 的影响为 - 2 * x−2∗x。
 * 即取反一个负数会使得结果变大，取反正数会使结果变小，取反 00 值对结果没有影响。
 * 因此，为了让取反后的结果尽可能的大，我们应当取反 −2∗x 尽可能大的数值。即按照「负数从小到大的顺序进行取反」。
 * 对取反次数 k 和 负数个数 cnt 进行分情况讨论：
 * k <= cnt按照负数从小到大的顺序进行取反即可；
 * k > cnt按照负数从小到大的顺序进行取反后，根据「是否存在 0 值」和「剩余取反次数的奇偶性」进行分情况讨论：
 * 存在 0 值 或 剩余取反次数为偶数：直接返回当前取反数组的总和（ 0 值可抵消任意次数的取反操作，将偶数次的取反操作应用在同一数值上，结果不变）；
 * 不存在 0 值且剩余取反次数为奇数：此时从当前数值中取一个绝对值最小值（使用 idx 记录该值下标）进行取反，得到最终的取反数组。
 * 最后对取反数组进行求和操作即可。
 */
public class K次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int n =nums.length,idx=0;//记录最小值下标
        PriorityQueue<Integer> q=new PriorityQueue<>((a,b)->nums[a]-nums[b]);
        boolean zero=false;
        for (int i = 0; i <n ; i++) {
            if (nums[i]<0) q.add(i);
            if (nums[i] == 0) zero=true;
            if (Math.abs(nums[i])<Math.abs(nums[idx])) idx=i;
        }
        if (k<q.size()){//k<=cnt：按照负数从小到大的顺序进行取反即可
            while (k-- > 0) nums[q.peek()]=-nums[q.poll()];
        }else {
            while (!q.isEmpty() && k-- > 0) nums[q.peek()]= -nums[q.poll()];//存在 0 值 或 剩余取反次数为偶数：直接返回当前取反数组的总和
            if (!zero && k % 2!=0) nums[idx]=-nums[idx];//不存在 0 值且剩余取反次数为奇数：此时从当前数值中取一个绝对值最小值
        }
        int ans=0;
        for (int i:nums) ans+=i;
        return ans;
    }
}
//时间复杂度：对 nums 进行遍历，得到 idx 以及优先队列的复杂度为 O(nlogn)；
// 从优先队列中取出元素进行取反操作的复杂度为 O(klogn)；对取反数组进行求和复杂度为 O(n)。
// 整体复杂度为 O(nlogn)
//空间复杂度：O(n)
