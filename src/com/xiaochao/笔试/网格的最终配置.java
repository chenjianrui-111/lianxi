package com.xiaochao.笔试;

import java.util.Arrays;

/**
 * 你想要在一个由n行m列组成的二维网格上实现一个简单的重力模拟。一些网格单元可能包含障碍物，一些可能包含单个苹果，而其他所有单元都是空的。我们制定一些规则:1.障碍物不会移动。2.每当苹果下方有一个空单元格时，苹果就会移动到空单元格中。在所有苹果都安顿好之后，找到网格的最终配置。
 * Input：输入为二维网格。障碍物用#表示，苹果用0表示，空单元格用’，表示。
 * Output：返回执行上述规则后网格的最终配置。
 * 输入：[[o,o,o],[#,.,.],[.,.,#]] 输出[[o,.,.],[#,.,o],[.,o,#]]
 */
/**
 * 为了解决这个问题，我们可以按照以下步骤处理每一列：
 * 1.遍历每一列。
 * 2.对于每一列，遍历每一行，从底部开始。
 * 3.如果遇到苹果，则将苹果移动到当前列中当前行以下最近的空单元格，然后将原来的位置置为空。
 * 4.如果遇到障碍物，则将当前列中当前行以下的所有苹果置为空。
 */
public class 网格的最终配置 {
    public static void main(String[] args) {
        char[][] inputGrid = {
                {'o', 'o', 'o'},
                {'#', '.', '.'},
                {'.', '.', '#'}
        };

        char[][] result = applyGravity(inputGrid);
        for (char[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static char[][] applyGravity(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int col = 0; col < m; col++) {
            int emptyRow = -1;
            for (int row = n - 1; row >= 0; row--) {
                if (grid[row][col] == 'o') {
                    if (emptyRow != -1) {
                        grid[emptyRow][col] = 'o';
                        grid[row][col] = '.';
                        emptyRow--;
                    }
                } else if (grid[row][col] == '.') {
                    if (emptyRow == -1) {
                        emptyRow = row;
                    }
                } else if (grid[row][col] == '#') {
                    emptyRow = -1;
                }
            }
        }
        return grid;
    }
}
