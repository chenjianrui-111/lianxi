package com.leixing.递归迭代.DFS;

/**
 *编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 *
 * 回溯解法
 * 和 N 皇后一样，是一道回溯解法裸题。
 * 上一题「36. 有效的数独（中等）」是让我们判断给定的 borad 是否为有效数独。
 * 这题让我们对给定 board 求数独，由于 board 固定是 9*9 的大小，我们可以使用回溯算法去做。
 * 这一类题和 N 皇后一样，属于经典的回溯算法裸题。
 * 这类题都有一个明显的特征，就是数据范围不会很大，如该题限制了范围为 9*9，而 N 皇后的 N 一般不会超过 13。
 * 对每一个需要填入数字的位置进行填入，如果发现填入某个数会导致数独解不下去，则进行回溯
 */
public class 解数独 {
    boolean [][] row=new boolean[9][9];
    boolean [][] col=new boolean[9][9];
    boolean [][][] cell=new boolean[3][3][9];
    public void solveSudoku(char[][] board) {
       for (int i=0;i<9;i++){
           for (int j=0;j<9;j++){
               if (board[i][j] !='.'){
                   int t=board[i][j] - '1';
                   row[i][t] = col[j][t] =cell[i / 3][j / 3][t] =true;
               }
           }
       }
       dfs(board,0,0);
    }
    boolean dfs(char[][]board,int x,int y){
        if (y == 9) {
            return dfs(board,x + 1,0);
        }
        if (x == 9) {
            return true;
        }
        if (board[x][y] !='.') {
            return dfs(board,x,y+1);
        }
        for (int i=0;i < 9;i++){
            if (!row[x][i] && !col[y][i] && !cell[x / 3][y / 3][i]){
                board[x][y] =(char)(i + '1');
                row[x][i] = col[y][i] =cell[x / 3][y / 3][i]=true;
                if (dfs(board,x,y+1)){
                    break;
                }else {
                    board[x][y] = '.';
                    row[x][i] = col[y][i] =cell[x / 3][y / 3][i]=false;
                }
            }
        }
        return board[x][y] !='.';
    }
}
/*
 * 时间复杂度：在固定 9*9 的棋盘里，具有一个枚举方案的最大值（极端情况，假设我们的棋盘刚开始是空的，
 * 这时候每一个格子都要枚举，每个格子都有可能从 1 枚举到 9，所以枚举次数为 999 = 729），即复杂度不随数据变化而变化。复杂度为 O(1)
 * 空间复杂度：在固定 9*9 的棋盘里，复杂度不随数据变化而变化。复杂度为 O(1)
 */
