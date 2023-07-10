package com.xiaochao.前缀和;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * 示例 1：
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 106
 * 1 <= k <= m * n
 */

import java.util.PriorityQueue;

/**
 * 二维前缀异或 & 优先队列（堆）
 * 创建二维数组 sum[][]，令 sum[i][j] 为以 (i, j) 为右下角的子矩阵的异或和，我们可以得出
 * 计算公式：sum[i][j] = sum[i - 1][j] + sum[i][j-1] -sum[i - 1][j - 1] + matrix[i - 1][j - 1]
 * 剩下的问题是，如果从所有的「子矩阵异或和」找到第 k 大的值。
 * 变成了 Top K 问题，可以使用「排序」或「堆」进行求解。
 * 具体的，我们可以建立一个大小为 k 的小根堆，在计算二维前缀异或时，判断当前「子矩阵异或和」是否大于堆顶元素：
 * • 大于堆顶元素：当前子矩阵异或和可能是第 k 大的值，堆顶元素不可能为第 k 大的
 * 值。将堆顶元素弹出，并将当前子矩阵和加入堆中
 * sum[i][j] = sum[i − 1][j] ⊕ sum[i][j − 1] ⊕ sum[i − 1][j − 1] ⊕ matrix[i − 1][j − 1]
 * • 小于堆顶元素：不会是第 k 大的值，直接丢弃。
 * • 等于堆顶元素：有相同元素在堆中，直接丢弃。
 * 最终的堆顶元素即为答案。
 */
public class 找出第K大的异或坐标值 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        PriorityQueue<Integer> pq =new PriorityQueue<>(k,(a,b)->a - b);
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] ^ sum[i][j - 1] ^ sum[i - 1][j  - 1] ^ matrix[i - 1][j - 1];
                if (pq.size() < k){
                    pq.add(sum[i][j]);
                }else {
                    if (sum[i][j] > pq.peek()){
                        pq.poll();
                        pq.add(sum[i][j]);
                    }
                }
            }
        }
        return pq.peek();
    }
}
//• 时间复杂度：O(m ∗ n ∗ log k) • 空间复杂度：O(m ∗ n)
