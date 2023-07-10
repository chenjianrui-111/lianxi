package com.xiaochao.贪心;

/**
 * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
 * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 * 示例 1：
 * 输入：target = [5,1,3], arr = [9,4,2,3,4]
 * 输出：2
 * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
 * 示例 2：
 * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * 输出：3
 * 提示：
 * 1 <= target.length, arr.length <= 105
 * 1 <= target[i], arr[i] <= 109
 * target 不包含任何重复元素。
 */

import java.util.*;

/**
 * 为了方便，我们令 target 长度为 n，arr 长度为 m ，target 和 arr 的最长公共子序列长度为 max，不难发现最终答案为 n - max。
 * 因此从题面来说，这是一道最长公共子序列问题（LCS）。
 * 但朴素求解 LCS 问题复杂度为 O(N*M) ，使用状态定义「 为考虑 a 数组的前 i 个元素和 b 数组的前 j 个元素的最长公共子序列长度为多少」进行求解。
 * 而本题的数据范围为 10^5 ，使用朴素求解 LCS 的做法必然超时。
 * 一个很显眼的切入点是 target 数组元素各不相同，当 LCS 问题增加某些条件限制之后，会存在一些很有趣的性质。
 * 其中一个经典的性质就是：当其中一个数组元素各不相同时，最长公共子序列问题（LCS）可以转换为最长上升子序列问题（LIS）进行求解。同时最长上升子序列问题（LIS）存在使用「维护单调序列 + 二分」的贪心解法，复杂度为 O(Nlogn)。
 * 因此本题可以通过「抽象成 LCS 问题」->「利用 target 数组元素各不相同，转换为 LIS 问题」->「使用 LIS 的贪心解法」，做到 O(nlogn) 的复杂度。
 */
// 本题使用: 哈希 + 贪心 + 二分
// 转化: 最长公共子序列lcs(longest_common_subsequence), 最长递增子序列lis(longest_increase_subsequence)
// 朴素的想法是求target与arr的最长公共子序列lcs, 然后用target.len() - lcs.len()即可
// 但题目中给出的数据范围target.len() 与 arr.len()的长度n可能到达10^5, 无法用O(n^2)的动态规划解法

// 这时候就体现出了困难题的困难, 需要十分巧妙的转化(说不定还要转化很多次)将其转换为我们熟知的类型
// 看例1:
// tar = [5, 1, 3],  arr = [9, 4, 2, 3, 4]
// 因为(1). 只涉及给arr'增加'元素。 (2). 最终答案与arr的长度也无关。
// 据此我们可以把arr中出现的, 且在tar中不存在的元素去掉, 其并不会有什么影响
// tar = [5, 1, 3], arr' = [3]
// 这时候我心里有了些感觉, 但例1元素太少还是不够直观, 我们再用例2试一次
// 例2:
// tar = [6, 4, 8, 1, 3, 2],  arr = [4, 7, 6, 2, 3, 8, 6, 1]
// tar = [6, 4, 8, 1, 3, 2], arr' = [4, 6, 2, 3, 8, 6, 1]
// 这时候注意到tar中元素无重复, 这个性质就像是索引一样, 我们当然就可以把他们当成索引, 得到一个新性质: 有序
// 其实tar本身就像是一种自定义的有序集合: 我们可以人为设定 4比6大(或小), 8比4大(或小) 等等
// 但为了方便后续的比较使用, 我们可以让其每个数字映射其对应的数组下标:
// idx = [0, 1, 2, 3, 4, 5]
// tar = [6, 4, 8, 1, 3, 2]
// (tar -> idx) = [(6 -> 0), (4 -> 1), (8 -> 2), (1 -> 3), (3 -> 4), (2 -> 5)]
// 这样, arr' = [4, 6, 2, 3, 8, 6, 1] -> [1, 0, 5, 4, 2, 0, 3]
// 经过上述变换, 相当于我们把原始的tar与arr分别变为
// tar' = [0, 1, 2, 3, 4, 5]
// arr' = [1, 0, 5, 4, 2, 0, 3]
// 其中tar'是递增的顺序集合, 而arr'是一种乱序集合
// 这时候我们只需要找到arr'中最长的符合严格单调递增性质的子序列lis长度即可
//转为最长公共子序列问题，再转为最长递增子序列问题
//当一个数组元素不重复时，最长公共子序列问题可以转换为最长递增子序列问题，从而可以将O(m*n)时间复杂度降为O(m + N*logN)
//时间O(M + N*logN)
//空间：O(m+n)
public class 得到子序列的最少操作次数 {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length, m = arr.length;
        Map<Integer,Integer> map = new HashMap<>();
        //target 中num -> 对应index
        for (int i = 0; i < n ; i++) {
            map.put(target[i],i);
        }
        //将 arr数转成对应 target中的下标数组，如果target不存在则忽略
        ArrayList<Integer> list =new ArrayList<>();
        for (int i : arr) {
            if (map.containsKey(i)) list.add(map.get(i));
        }
        int[] arr1 = list.stream().mapToInt(Integer::intValue).toArray();
        //求出arr1的最长递增子序列
        return n - lis(arr1);
    }
    int lis(int[] arr){
        if (arr.length == 0) return 0;
        int m = arr.length;
        //ends[i]:长度为 i+1 的LIS中，最小的结尾元素，单调递增
        int[] ends =new int[m];
        ends[0] = arr[0];
        int max = 1,endSize = 1;//endSize:当前ends数组的有效个数
        for (int i = 1; i < m; i++) {
            int num = arr[i];
            int index = findTheMostRightIndexOfSmallerThan(ends, endSize, num);
            // index: 小于num的最右位置，num落在它的后面setIndex，形成一个更长的LIS
            int setIndex = index+1;
            ends[setIndex] = num;
            int size = setIndex + 1;
            endSize = Math.max(endSize, size);
            max = Math.max(max, size);
        }
        return max;
    }
    // 在arr[0 ... size-1]上二分查找：小于num的最右的元素下标，如果不存在，返回-1
    private static int findTheMostRightIndexOfSmallerThan(int[] arr, int size, int num) {
        int l = 0, r = size-1, index = -1;
        while (l <= r) {
            int m = l + (r-l)/2;
            if (arr[m] < num) {
                index = m;
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return index;
    }
}
class Solution {
    public int minOperations(int[] t, int[] arr) {
        int n = t.length, m = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(t[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int x = arr[i];
            if (map.containsKey(x)) list.add(map.get(x));
        }
        int len = list.size();
        int[] f = new int[len], g = new int[len + 1];
        Arrays.fill(g, Integer.MAX_VALUE);
        int max = 0;
        for (int i = 0; i < len; i++) {
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < list.get(i)) l = mid;
                else r = mid - 1;
            }
            int clen = r + 1;
            f[i] = clen;
            g[clen] = Math.min(g[clen], list.get(i));
            max = Math.max(max, clen);
        }
        return n - max;
    }
}
//时间复杂度：通过 O(n) 复杂度得到 target 的下标映射关系；
// 通过 O(M) 复杂度得到映射数组list ；贪心求解 LIS 的复杂度为 O(mlogm)。整体复杂度为 O(n+mlogm)
//空间复杂度：O(n+m)
