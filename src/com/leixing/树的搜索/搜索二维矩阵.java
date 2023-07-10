package com.leixing.树的搜索;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 抽象 BST 解法
 * 我们可以将二维矩阵抽象成「以右上角为根的 BST」：
 * 那么我们可以从根（右上角）开始搜索，如果当前的节点不等于目标值，可以按照树的搜索顺序进行：
 * 当前节点「大于」目标值，搜索当前节点的「左子树」，也就是当前矩阵位置的「左方格子」，即 y--
 * 当前节点「小于」目标值，搜索当前节点的「右子树」，也就是当前矩阵位置的「下方格子」，即 x++
 *
 */
public class 搜索二维矩阵 {
    int m, n;

    public boolean searchMatrix(int[][] matrix, int target) {
          m=matrix.length;n=matrix[0].length;
         int x=0, y= n-1;
         while (check(x,y) && matrix[x][y] !=target){
             if (matrix[x][y] > target){
                 y--;
             }else {
                 x++;
             }
         }
         return check(x,y) && matrix[x][y] == target;
    }
    public boolean check(int x,int y){
        return x >=0 && x < m && y >=0 && y< n;
    }
}
