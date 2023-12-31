package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * 示例：
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 *
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 */
public class StockSpanner {
    // int[] 记录 {价格，小于等于该价格的天数} 二元组
    Stack<int[]> stk = new Stack<>();

    public int next(int price) {
        // 算上当天
        int count = 1;
        // 单调栈模板
        while (!stk.isEmpty() && price >= stk.peek()[0]) {
            // 挤掉价格低于 price 的记录
            int[] prev = stk.pop();
            // 计算小于等于 price 的天数
            count += prev[1];
        }
        stk.push(new int[]{price, count});

        return count;
    }
}
