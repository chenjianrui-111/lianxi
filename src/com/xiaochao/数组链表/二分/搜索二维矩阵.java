package com.xiaochao.数组链表.二分;

/**
 *编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 只要知道二维数组的的行数 m 和列数 n，二维数组的坐标 (i, j) 可以映射成一维的 index = i * n + j；
 * 反过来也可以通过一维 index 反解出二维坐标 i = index / n, j = index % n。
 */
public class 搜索二维矩阵 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m =matrix.length , n=matrix[0].length;
        // 把二维数组映射到一维
        int left = 0, right = m * n - 1;
        // 前文讲的标准的二分搜索框架
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(get(matrix, mid) == target)
                return true;
            else if (get(matrix, mid) < target)
                left = mid + 1;
            else if (get(matrix, mid) > target)
                right = mid - 1;
        }
        return false;
    }
    // 通过一维坐标访问二维数组中的元素
    int get(int[][] matrix, int index) {
        int m = matrix.length, n = matrix[0].length;
        // 计算二维中的横纵坐标
        int i = index / n, j = index % n;
        return matrix[i][j];
    }
}
