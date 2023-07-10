package com.xiaochao.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 */
//单调栈
public class 柱状图中最大的矩形 {
    public int largestRectangleArea(int[] heights) {
        int len =heights.length;
        if (len == 0){
            return 0;
        }
        if (len == 1){
            return heights[0];
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int area = 0;
        for (int i = 0; i < len ; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];
                //有连续长度相等的数组时
                while (!stack.isEmpty() && heights[stack.peekLast()] == height){
                    stack.removeLast();
                }
                int width;
                if (stack.isEmpty()){
                    width = i;
                }else {
                    width = i - stack.peekLast() - 1;
                }
                area = Math.max(area,width * height);
            }
            stack.addLast(i);
        }
        //完成一次遍历成功后，弹出栈中其余元素
        while (!stack.isEmpty()) {
            int height = heights[stack.removeLast()];
            //有连续长度相等的数组时
            while (!stack.isEmpty() && heights[stack.peekLast()] == height) {
                stack.removeLast();
            }
            int width;
            if (stack.isEmpty()) {
                //我们假设栈的最右边有哨兵节点 比栈中任何一个元素的高度都小
                width = len;
            } else {
                width = len - stack.peekLast() - 1;
            }
            area = Math.max(area, width * height);
        }
        return area;
    }
}

/**
 * 运用哨兵节点 栈永远不会为空
 * 开头和结尾永远都会有 比栈中元素的高度小
 */
class Solution2{
    public int largestRectangleArea(int[] heights) {
        int len =heights.length;
        if (len == 0){
            return 0;
        }
        if (len == 1){
            return heights[0];
        }
        int area = 0;
        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len ; i++) {
            newHeights[i+1] = heights[i];
        }
        len += 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            while (heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];
                int width = i - stack.peekLast() - 1;
                area = Math.max(area,width * height);
            }
            stack.addLast(i);
        }
        return area;
    }
}
//O(n)
