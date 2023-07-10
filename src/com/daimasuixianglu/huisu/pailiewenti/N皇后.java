package com.daimasuixianglu.huisu.pailiewenti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 首先来看一下皇后们的约束条件：
 *
 * 不能同行
 * 不能同列
 * 不能同斜线
 */
public class N皇后 {
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTracing(n, 0, chessboard);
        return res;
    }
    // n 为输入的棋盘大小
    // row 是当前递归到棋牌的第几行了
    public void backTracing(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (isValid(row, col, n, chessboard)) {// 验证合法就可以放
                chessboard[row][col] = 'Q';// 放置皇后
                backTracing(n, row + 1, chessboard);
                chessboard[row][col] = '.';// 回溯，撤销皇后
            }
        }
    }
    public List Array2List(char[][] chessboard){
        List<String> list=new ArrayList<>();
        for (char[] c:chessboard){
            list.add(String.copyValueOf(c));
        }
        return list;
    }
    public boolean isValid(int row,int col,int n,char[][] chessboard){
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
