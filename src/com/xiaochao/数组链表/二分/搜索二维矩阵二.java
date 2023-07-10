package com.xiaochao.数组链表.二分;

public class 搜索二维矩阵二 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        //初始化在右上角
        int i = 0 ,j = n - 1;
        while (i<m && j>= 0){
            if (matrix[i][j] == target){
                return true;
            }
            if (matrix[i][j] < target){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}
