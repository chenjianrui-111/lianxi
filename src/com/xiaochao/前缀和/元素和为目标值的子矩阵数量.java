package com.xiaochao.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 输出：4
 解释：四个只含 0 的 1x1 子矩阵。
 示例 2：
 输入：matrix = [[1,-1],[-1,1]], target = 0
 输出：5
 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 示例 3:
 输入：matrix = [[904]], target = 0
 输出：0
 提示：
 1 <= matrix.length <= 100
 1 <= matrix[0].length <= 100
 -1000 <= matrix[i] <= 1000
 -10^8 <= target <= 10^8
 */
public class 元素和为目标值的子矩阵数量 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] -sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m ; j++) {
                for (int p = 1; p <= i ; p++) {
                    for (int q = 1; q <= j;q++){
                        if (sum[i][j] - sum[p-1][j] - sum[i][q-1] + sum[p-1][q-1] == target) ans++;
                    }
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：O(m2 ∗ n2) • 空间复杂度：O(m ∗ n)
/**
 *优化枚举 + 哈希表
 * 上述分析是从「点」上来确定一个子矩阵，在 n 和 m 同阶的情况下，复杂度是 O(n4) 的。
 * 事实上，我们能从「边」的角度来确定一个子矩阵，通过枚举三条边，然后加速找第四条边的过
 * 程，这样可以将复杂度降到 O(n3)。
 * 这个分析思路在 （题解）363. 矩形区域不超过 K 的最大数值和 详细讲过，没有印象的同学可
 * 以翻翻。这道题一定程度上是那道题的简化版，在本题我们只需要找到矩阵和为 target 的值，
 * 因此只需要使用「哈希表」来记录出现过的值即可，而在 （原题）363. 矩形区域不超过 K 的最
 * 大数值和 中，我们需要找到和不超过 k 的最大子矩阵，因此还涉及「有序集合 + 二分」。
 * 具体的，我们仍然需要先预处理出一个二维前缀和。然后通过枚举确定「子矩阵的上下边界」，
 * 在将「子矩阵右边界」不断右移的过程中，把「子矩阵右边界」到「原矩阵左边界」行程的矩阵
 * 和存入哈希表（因为要统计数量，存储格式为 {"面积”:“出现次数”} ），然后通过容斥原理来找
 * 到目标的「子矩阵左边界」。
 * 假设当前我们「子矩阵的上下边界」已经固定，当枚举到某个「子矩阵右边界」时，我们先通过
 * 二维前缀和计算出「子矩阵右边界」和「原矩阵左边界」形成的矩阵和 cur，由于我们希望找
 * 到矩阵和为 target 的子矩阵，即希望找到一个「子矩阵左边界」使得矩阵和满足要求，这等价
 * 于从「哈希表」中找到一个 x，使得 cur − x = target，这是一个 O(1) 操作。
 */
class Solution15{
    public int numSubmatrixSumTarget(int[][] mat, int t) {
        int n = mat.length, m = mat[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m ; j++) {
                sum[i][j] = sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int top  = 1; top <= n ; top ++) {
            for (int bot = top; bot <= n ; bot++) {
                int cur = 0;
                Map<Integer,Integer> map = new HashMap<>();
                for (int r = 1; r <= m ; r++) {
                    cur = sum[bot][r] - sum[top - 1][r];
                    if (cur == t) ans++;
                    if (map.containsKey(cur - t)) ans += map.get(cur - t);
                    map.put(cur,map.getOrDefault(cur,0) + 1);
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：O(m ∗ n2) • 空间复杂度：O(m ∗ n)
/**
 * 进阶
 * 1. 【时间复杂度】在上述解法中，我们采用的是固定上下边界，移动右边界，通过哈
 * 希表找左边界的做法，复杂度为 O(m ∗ n2)；自然也能改为固定左右边界，移动下
 * 边界，通过哈希表找上边界做法，复杂度为 O(m2 ∗ n)。那么我们要如何调整代
 * 码，才能最大化「哈希表」带来的优化效果呢？此时的复杂度又是多少？
 * 2. 【空间复杂度】我们空间复杂度的瓶颈在「二维前缀和」上，但注意到无论我们采
 * 取「固定上下边界」还是「固定左右边界」的做法，扫描原矩阵的方向都是固定
 * 的，那么是否可以不预处理「二维前缀和」呢？从而将空间复杂度降低到
 * O(max(n, m)) 呢？
 */
