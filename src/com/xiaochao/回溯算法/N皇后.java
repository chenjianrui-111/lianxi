package com.xiaochao.回溯算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N皇后 {
    List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        char [][] board =new char[n][n];
        for (char[] c : board){
            Arrays.fill(c,'.');
        }
        backtrack(n,0,board);
        return res;
    }
    void backtrack(int n,int row,char[][] board){
        if (row == n){
            res.add(ArrayList(board));
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
    List ArrayList(char[][] board){
        List<String> list = new ArrayList<>();
        for (char[] c:board){
            list.add(String.copyValueOf(c));
        }
        return list;
    }
    boolean isValid(int row,int col,int n,char[][] board){
        //检查列
        for (int i = 0; i <row ; i++) {
            if (board[i][col] == 'Q'){
                return false;
            }
        }
        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
