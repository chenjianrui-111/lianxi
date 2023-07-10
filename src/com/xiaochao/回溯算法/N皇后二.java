package com.xiaochao.回溯算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */
public class N皇后二 {
    int count = 0;
    public int totalNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
            char [][] board =new char[n][n];
            for (char[] c : board){
                Arrays.fill(c,'.');
            }
            backtrack(n,0,board);
            return count++;
        }
        void backtrack(int n,int row,char[][] board){
            if (row == n){
                count++;
                return;
            }
            for (int col = 0;col < n;col++){
                if (isValid(row,col,n,board)){
                    board[row][col] = 'Q';
                    backtrack(n,row+1,board);
                    board[row][col] = '.';
                }
            }
        }

    boolean isValid(int row, int col, int n, char[][] board) {
        //检查列
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
