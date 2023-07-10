package com.xiaochao.队列和栈.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 */
public class 每日温度 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 这里放元素索引，而不是元素
        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                s.pop();
            }
            // 得到索引间距
            res[i] = s.isEmpty() ? 0 : (s.peek() - i);
            //将索引入栈，而不是元素
            s.push(i);
        }
        return res;
    }

/**
 *抽象题意为 : 求解给定序列中每个位置（右边）最近一个比其大的位置，可使用「单调栈」来进行求解。
 * 具体的，我们可以从前往后处理所有的 ts[i]，使用某类容器装载我们所有的「待更新」的位置（下标），假设当前处理到的是 ts[i]：
 * 若其比容器内的任意位置（下标）对应温度要低，其必然不能更新任何位置（下标），
 * 将其也加入容器尾部（此时我们发现，若有一个新的位置（下标）加入容器，其必然是当前所有待更新位置（下标）中的温度最低的，即容器内的温度单调递减）；
 * 若其价格高于容器内的任一位置（下标）对应温度，其能够更新容器位置（下标）的答案，
 * 并且由于我们容器满足单调递减特性，我们必然能够从尾部开始取出待更新位置来进行更新答案，直到处理完成或遇到第一个无法更新位置。
 * 由于我们需要往尾部添加和取出元素，因此容器可使用「栈」。
 */
      public int[] dailyTemperatures2(int[] ts) {
          int n = ts.length;
          int[] ans = new int[n];
          Deque<Integer> deque = new ArrayDeque<>();
          for (int i = 0; i < n ; i++) {
              while (!deque.isEmpty() && ts[deque.peekLast()] < ts[i]){
                  int idx = deque.pollLast();
                  ans[idx] = i - idx;
              }
              deque.addLast(i);
          }
          return ans;
      }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
