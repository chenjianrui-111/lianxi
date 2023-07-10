package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

/**
给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
 输入：prices = [8,4,6,2,3]
 输出：[4,2,4,2,3]
 解释：
 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
 商品 3 和 4 都没有折扣。
 */
public class 商品折扣后的最终价格 {
    public int[] finalPrices(int[] prices) {
        int n =prices.length;
        int[] res =new int[n];
        // 下一个小于等于 price[i] 的价格就是优惠券折扣
        int[] nextElement = nextLessOrEqualElement(prices);
        for (int i = 0; i < prices.length; i++) {
            // 如果存在优惠券，则减少相应的价格
            if (nextElement[i] != -1) {
                res[i] = prices[i] - nextElement[i];
            } else {
                res[i] = prices[i];
            }
        }
        return res;
    }
    // 单调栈模板：计算 nums 中每个元素的下一个更小或相等的元素
    int[] nextLessOrEqualElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 删掉 nums[i] 后面较大的元素
            while (!s.isEmpty() && s.peek() > nums[i]) {
                s.pop();
            }
            // 现在栈顶就是 nums[i] 身后的更小或相等元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }
}
