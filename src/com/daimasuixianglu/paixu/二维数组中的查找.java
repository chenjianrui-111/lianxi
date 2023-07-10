package com.daimasuixianglu.paixu;

/**
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 * 给定 target = 3，返回 false。
 * 数据范围：矩阵的长宽满足  ， 矩阵中的值满足
 * 进阶：空间复杂度  ，时间复杂度
 * 示例1
 * 输入
 * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 输出
 * true
 * 说明
 * 存在7，返回true
 * 示例2
 * 输入
 * 1,[[2]]
 * 输出
 * false
 * 示例3
 * 输入
 * 3,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 输出
 * false
 * 说明
 * 不存在3，返回false
 */
public class 二维数组中的查找 {
    public boolean Find(int target, int [][] array) {
        int row =array.length,col=array[0].length;
        int i=0,j=col-1;
        while (i<row && j>=0){
            if (array[i][j] == target){
                return true;
            }
            if (array[i][j] > target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }
}
