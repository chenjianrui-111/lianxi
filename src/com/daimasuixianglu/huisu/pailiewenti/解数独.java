package com.daimasuixianglu.huisu.pailiewenti;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class 解数独 {
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
    public boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i <9 ; i++) {// 遍历行
            for (int j = 0; j < 9; j++) {// 遍历列
                if (board[i][j] != '.') {// 跳过原始数字
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {// (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)) {
                        board[i][j] = k;
                        if (solveSudokuHelper(board)) { // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    public boolean isValidSudoku(int row,int col,char val,char[][] board){
        //同行是否重复
        for (int i = 0; i <9 ; i++) {
            if (board[row][i] == val){
                return false;
            }
        }
        //同列是否重复
        for (int j = 0; j <9 ; j++) {
            if (board[j][col] == val){
                return false;
            }
        }
        int startRow=(row / 3) * 3;
        int startCol=(col / 3) * 3;
        for (int i = startRow; i <startRow+3 ; i++) {
            for (int j = startCol; j <startCol+3 ; j++) {
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
/**
 *子集问题分析：
 *
 * 时间复杂度：O(n * 2^n)，因为每一个元素的状态无外乎取与不取，所以时间复杂度为O(2^n)，构造每一组子集都需要填进数组，又有需要O(n)，最终时间复杂度：O(n * 2^n)
 * 空间复杂度：O(n)，递归深度为n，所以系统栈所用空间为O(n)，每一层递归所用的空间都是常数级别，注意代码里的result和path都是全局变量，就算是放在参数里，传的也是引用，并不会新申请内存空间，最终空间复杂度为O(n)
 * 排列问题分析：
 *
 * 时间复杂度：O(n!)，这个可以从排列的树形图中很明显发现，每一层节点为n，第二层每一个分支都延伸了n-1个分支，再往下又是n-2个分支，所以一直到叶子节点一共就是 n * n-1 * n-2 * ..... 1 = n!。
 * 空间复杂度：O(n)，和子集问题同理。
 * 组合问题分析：
 *
 * 时间复杂度：O(n * 2^n)，组合问题其实就是一种子集的问题，所以组合问题最坏的情况，也不会超过子集问题的时间复杂度。
 * 空间复杂度：O(n)，和子集问题同理。
 * N皇后问题分析：
 *
 * 时间复杂度：O(n!) ，其实如果看树形图的话，直觉上是O(n^n)，但皇后之间不能见面所以在搜索的过程中是有剪枝的，最差也就是O（n!），n!表示n * (n-1) * .... * 1。
 * 空间复杂度：O(n)，和子集问题同理。
 * 解数独问题分析：
 *
 * 时间复杂度：O(9^m) , m是'.'的数目。
 * 空间复杂度：O(n^2)，递归的深度是n^2
 */
