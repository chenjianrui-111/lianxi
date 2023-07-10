package com.xiaochao.图;

import java.util.Arrays;
import java.util.Stack;

//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
public class 最大矩形 {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[n];         // 柱高
        for (int row = 0; row < m; row++) {
            // 一行一行遍历，保存柱高
            // 一层一层遍历，保存柱高，相当于计算柱状图中的最大矩形
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestArea(heights));
        }
        return maxArea;
    }
    // LeetCode 84
    public int largestArea(int[] heights) {
        int n = heights.length;
        if (n == 0) {
            return 0;
        }

        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }
}
