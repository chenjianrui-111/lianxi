package com.cjr.paixu;

/**
 * 从左下角开始：上边的数小于当前数，右边的数大于当前数
 * 从右上角开始：左边的数小于当前数，下边的数大于当前数
 * 类似二叉排序树，利用这一特性进行搜索减少搜索空间
 * 比如从右上角开始：
 * 如果matrix[i][j]>target,就可以排除matrix[][j]这一列,target必定不在这一列,j--;
 * 如果matrix[i][j]<target,就可以排除matrix[i][]这一行,target必定不在这一行,i++;
 *
 */
public class souSuoErWeiJuZhen {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i=0,j=matrix[0].length-1;
        while (i<matrix.length && j>=0){
            if (matrix[i][j]>target) j--;
            else if (matrix[i][j]<target) i++;
            else return true;
        }
        return false;
    }
}
