package com.xiaochao.前缀和;

/**
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 输入:
 ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 输出:
 [null, 8, 11, 12]
 解释:
 NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 提示：
 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 200
 -105 <= matrix[i][j] <= 105
 0 <= row1 <= row2 < m
 0 <= col1 <= col2 < n
 最多调用 104 次 sumRegion 方法
 */
public class 二维区域和检索矩阵不可变 {
    int[][] sum;

    public 二维区域和检索矩阵不可变(int[][] matrix) {
        int n = matrix.length, m = n == 0 ? 0 : matrix[0].length;
// 与「一维前缀和」一样，前缀和数组下标从 1 开始，因此设定矩阵形状为 [n + 1][m + 1]（模板部分）
        sum = new int[n + 1][m + 1];
// 预处理除前缀和数组（模板部分）
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 当前格子(和) = 上方的格子(和) + 左边的格子(和) - 左上角的格子(和) + 当前格子(值)
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int x1, int y1, int x2, int y2) {
// 求某一段区域和 [i, j] 的模板是 sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
// 但由于我们源数组下标从 0 开始，因此要在模板的基础上进行 + 1
        // 前缀和是从 1 开始，原数组是从 0 开始，上来先将原数组坐标全部 +1，转换为前缀和坐标
        x1++;
        y1++;
        x2++;
        y2++;
        // 也可以记作 22 - 12(x - 1) - 21(y - 1) + 11(x y 都 - 1)
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }
}
//• 时间复杂度：预处理前缀和数组需要对原数组进行线性扫描，复杂度为 O(n ∗ m) ，计算结果复杂度为 O(1)。
// 整体复杂度为 O(n ∗ m) • 空间复杂度：O(n ∗ m)
