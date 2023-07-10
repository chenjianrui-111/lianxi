package com.xiaochao.贪心;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 示例：
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 * 使用贪心思想扩展数对链，在所有可作为下一个数对的集合中选择第二个数最小的数对添加到数对链。
 * 算法
 * 根据思路中的描述，按照数对第二个数的升序序列遍历所有数对，如果当前数对可以加入链，则加入。
 */
public class 最长数对链 {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair: pairs) if (cur < pair[0]) {
            cur = pair[1];
            ans++;
        }
        return ans;
    }
}
