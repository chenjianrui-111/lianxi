package com.xiaochao.笔试;

/**
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 提示：
 * 1 <= n <= 9
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 方法一：基于集合的回溯
 * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 columns、diagonals1、diagonals2
 * 分别记录每一列以及两个方向的每条斜线上是否有皇后。
 * 列的表示法很直观，一共有 N 列，每一列的下标范围从 0 到 N−1，使用列的下标即可明确表示每一列。
 * 如何表示两个方向的斜线呢？对于每个方向的斜线，需要找到斜线上的每个位置的行下标与列下标之间的关系。
 * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如 (0,0) 和 (3,3) 在同一条方向一的斜线上。因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
 * 每次放置皇后时，对于每个位置判断其是否在三个集合中，如果三个集合都不包含当前位置，则当前位置是可以放置皇后的位置
 */
public class N皇后二 {
    public int totalNQueens(int n) {
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        return backtrack(n,0,columns,diagonals1,diagonals2);
    }
    public int backtrack(int n,int row,Set<Integer> columns,Set<Integer> diagonals1,Set<Integer> diagonals2){
        if (row == n){
            return 1;
        }
        else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)){
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)){
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)){
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n,row + 1,columns,diagonals1,diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }
}
//时间复杂度：O(N!)，其中 N 是皇后数量。
//空间复杂度：O(N)，其中 N 是皇后数量。空间复杂度主要取决于递归调用层数以及三个集合，递归调用层数不会超过 N，每个集合的元素个数都不会超过 N。
