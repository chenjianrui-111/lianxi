package com.xiaochao.前缀和;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 */

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 朴素二维前缀和 从题面来看显然是一道「二维前缀和」的题目，如果你还不了解「二维前缀和」，可以看看
 * （题解）304. 二维区域和检索 - 矩阵不可变。本题预处理前缀和的复杂度为 O(m ∗ n)。
 * 搜索所有子矩阵需要枚举「矩形左上角」和「矩形右下角」，复杂度是 O(m2 ∗ n2)。
 * 因此，如果把本题当做二维前缀和模板题来做的话，整体复杂度是 O(m2 ∗ n2)。
 * 数据范围是 102，对应的计算量是 108，理论上会超时，但当我们枚举「矩形左上角」(i, j) 的
 * 时候，我们只需要搜索位于 (i, j) 的右下方的点 (p, q) 作为「矩形右下角」，所以其实我们是
 * 取不满 m2 ∗ n2 的，但仍然具有超时风险（2021/04/20 Java 测试可通过，C++ 使用 vector
 * 会 TLE）
 */
public class 矩形区域不超过K的最大数值和 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m+1][n+1];
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int p = i; p <= m; p++) {
                    for (int q = j; q <= n; q++) {
                        int cur = sum[p][q] - sum[i - 1][q] - sum[p][j - 1] + sum[i - 1][j - 1];
                        if (cur <= k) {
                            ans = Math.max(ans, cur);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：预处理前缀和数组复杂度为 O(m ∗ n)，查找答案的复杂度为 O(m2 ∗ n2)。
// 整体复杂度为 O(m2 ∗ n2)。 • 空间复杂度：O(m ∗ n)
/**
 *前缀和 & 二分（抽象一维） 我们来细想一下「朴素二维前缀和」解法是如何搜索答案（子矩阵）：通过枚举「左上
 * 角」&「右下角」来唯一确定某个矩阵。 换句话说是通过枚举 (i, j) 和 (p, q) 来唯一确定子矩阵的四条边，每个坐标点可以看作确定子
 * 矩阵的某条边。
 * 既然要确定的边有四条，我们可以如何降低复杂度呢？
 * 简单的，我们先思考一下同样是枚举的 1. 两数之和 问题。
 * 在 1. 两数之和 中可以暴力枚举两个数，也可以只枚举其中一个数，然后使用数据结构（哈希
 * 表）来加速找另一个数（这是一个通用的「降低枚举复杂度」思考方向）。
 * 对应到本题，我们可以枚举其中三条边，然后使用数据结构来加速找第四条边。
 * 当我们确定了三条边（红色）之后，形成的子矩阵就单纯取决于第四条边的位置（黄色）：
 * 于是问题转化为「如何快速求得第四条边（黄色）的位置在哪」。
 * 我们可以进一步将问题缩小，考虑矩阵只有一行（一维）的情况
 * 这时候问题进一步转化为「在一维数组中，求解和不超过 K 的最大连续子数组之和」。
 * 对于这个一维问题，我们可以先预处理出「前缀和」，然后枚举子数组的左端点，然后通过「二
 * 分」来求解其右端点的位置。
 * 假定我们已经求得一维数组的前缀和数组 sum ，即可得下标范围 [i, j] 的和为：
 * 经过变形后得：
 * 我们有两种思路来最大化 areaSum(i, j)： • 确定（枚举）左端点位置 i ，求得符合条件的最大右端点 sum[j]
 * • 确定（枚举）右端点位置 j ，求得符合条件的最小左端点 sum[i]
 * 对于没有负权值的一维数组，我们可以枚举左端点 i ，同时利用前缀和的「单调递增」特性，
 * 通过「二分」找到符合 sum[j] ⩽ k + sum[i − 1] 条件的最大值 sum[j] ，从而求解出答案。
 * 但是如果是含有负权值的话，前缀和将会丢失「单调递增」的特性，我们也就无法使用枚举 i 并结合「二分」查找 j 的做法。
 * 这时候需要将过程反过来处理：我们从左到右枚举 j ，并使用「有序集合」结构维护遍历过的
 * 位置，找到符合 sum[j] − k ⩽ sum[i] 条件的最小值 sum[i] ，从而求解出答案。
 * 基于上述分析，解决这样的一维数组问题复杂度是 O(n log n) 的。
 * 将这样的思路应用到二维需要一点点抽象能力。
 * areaSum(i, j) = sum[j] − sum[i − 1] ⩽ k sum[j] ⩽ k + sum[i − 1]
 * 同时，将一维思路应用到本题（二维），复杂度要么重点是如何与「一维」问题进行关联：显然「目标子矩阵的和」等于「子矩阵的右边列 与 原矩
 * 阵的左边列 形成的子矩阵和」-「子矩阵左边列 与 原矩阵左边列 形成的子矩阵和」
 * 我们可以使用 area[r] 代表「子矩阵的右边列 与 原矩阵的左边列 形成的子矩阵和」，使用
 * area[l - 1] 代表「子矩阵的左边列 与 原矩阵的左边列 形成的子矩阵和」的话，则有：
 * 这与我们「一维问题」完全一致，同时由「上下行」&「右边列」可以直接确定 area[r] 的大
 * 小，通过「有序集合」存储我们遍历 r 过程中的所有的 area[r] 从而实现「二分」查找符合
 * area[r] − k ⩽ area[l − 1] 条件的 最小 的 area[l - 1] 。
 * 至此，我们通过预处理前缀和 + 容斥原理彻底将题目转化为「一维问题」进行来求解。 O(m2 ∗ n log n) 要么是 O(n2 ∗ m log m)。 我们先不考虑「最大化二分收益」问题，先假设我们是固定枚举「上下行」和「右边列」，这时
 * 候唯一能够确定一个子矩阵则是取决于「左边列」：
 * 重点是如何与「一维」问题进行关联：显然「目标子矩阵的和」等于「子矩阵的右边列 与 原矩
 * 阵的左边列 形成的子矩阵和」-「子矩阵左边列 与 原矩阵左边列 形成的子矩阵和」
 * 我们可以使用 area[r] 代表「子矩阵的右边列 与 原矩阵的左边列 形成的子矩阵和」，使用
 * area[l - 1] 代表「子矩阵的左边列 与 原矩阵的左边列 形成的子矩阵和」的话，则有：
 *     target = area[r] - area[l-1] <= k
 * 这与我们「一维问题」完全一致，同时由「上下行」&「右边列」可以直接确定 area[r] 的大
 * 小，通过「有序集合」存储我们遍历 r 过程中的所有的 area[r] 从而实现「二分」查找符合
 * area[r] − k ⩽ area[l − 1] 条件的 最小 的 area[l - 1] 。
 * 至此，我们通过预处理前缀和 + 容斥原理彻底将题目转化为「一维问题」进行来求解。
 */
class Solution5 {
    public int maxSumSubmatrix(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
// 预处理前缀和
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = Integer.MIN_VALUE;
// 遍历子矩阵的上边界
        for (int top = 1; top <= m; top++) {
// 遍历子矩阵的下边界
            for (int bot = top; bot <= m; bot++) {
// 使用「有序集合」维护所有遍历到的右边界
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(0);
// 遍历子矩阵的右边界
                for (int r = 1; r <= n; r++) {
// 通过前缀和计算 right
                    int right = sum[bot][r] - sum[top - 1][r];
// 通过二分找 left
                    Integer left = ts.ceiling(right - k);
                    if (left != null) {
                        int cur = right - left;
                        ans = Math.max(ans, cur);
                    }
// 将遍历过的 right 加到有序集合
                    ts.add(right);
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：枚举上下边界复杂度为 O(m2)；枚举右边界为 O(n)，使用
//TreeSet （基于红黑树）存储和查找左边界复杂度为 O(log n)。整体复杂度为 O(m2 ∗ n log n) • 空间复杂度：O(m ∗ n)
/**
 *最大化「二分」效益
 * 上述解法中，我们先枚举的是「上下行」和「右边列」，然后通过 TreeSet 来「二分」出符合条件的「左边列」。
 * 事实上，我们需要将「二分过程」应用到数值较大的行或者列之中，这样才能最大化我们查找的
 * 效率（同时也回答了本题的进阶部分）。
 */
class Solution6{
    public int maxSumSubmatrix(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m+1][n+1];
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] -sum[i-1][j-1] + mat[i-1][j-1];
            }
        }
        //固定右边界
        boolean isRight = n > m;
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <=(isRight ? m : n) ; i++) {
            for (int j = i; j <= (isRight ? m : n) ; j++) {
                TreeSet<Integer> ts =new TreeSet<>();
                ts.add(0);
                for (int fixed = 1; fixed <= (isRight ? n : m); fixed++) {
                    int a = isRight ? sum[j][fixed] - sum[i - 1][fixed] : sum[fixed][j] - sum[fixed][i - 1];
                    Integer b = ts.ceiling(a - k);
                    if (b != null) {
                        int cur = a - b;
                        ans = Math.max(ans, cur);
                    }
                    ts.add(a);
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：预处理「每行」或「每列」的前缀和，复杂度为 O(m ∗ n)；枚举子 矩阵的「上下行」或「左右行」，复杂度为 O(min(m, n)2)；
// 结合二维前缀和套用一维最大连续子数组解决方案，复杂度为max(m, n)log max(m, n)。
// 整体复杂度为 O(min(m, n)2 ∗ max(m, n)log max(m, n)) • 空间复杂度：O(m ∗ n)
/**
 *空间优化
 * 不难发现，我们在原矩阵搜索目标子矩阵的过程是严格的「从上到下」&「从左到右」的。
 * 因此我们可以将计算前缀和的逻辑下放到搜索子矩阵的循环里去做，从而将 O(m ∗ n) 的空间
 * 复杂度下降到 O(max(m, n))
 */
class Solution7{
    public int maxSumSubmatrix(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        boolean isRight = n > m;
        int[] sum = isRight ? new int[n + 1] : new int[m + 1];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <=(isRight ? m : n) ; i++) {
            Arrays.fill(sum,-1);
            for (int j = i; j <= (isRight ? m : n) ; j++) {
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(0);
                int a = 0;
                for (int fixed = 1; fixed <= (isRight ? n : m) ; fixed++) {
                    sum[fixed] += isRight ? mat[j - 1][fixed - 1] : mat[fixed - 1][j - 1];
                    a += sum[fixed];
                    Integer b= ts.ceiling(a - k);
                    if ( b != null){
                        int cur = a -b;
                        ans = Math.max(ans,cur);
                    }
                    ts.add(a);
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：预处理「每行」或「每列」的前缀和，复杂度为 O(m ∗ n)；枚举子
//矩阵的「上下行」或「左右行」，复杂度为 O(min(m, n)2)；结合二维前缀和套
//用一维最大连续子数组解决方案，复杂度为max(m, n)log max(m, n)。整体复
//杂度为 O(min(m, n)2 ∗ max(m, n)log max(m, n)) • 空间复杂度：O(max(m, n))
