package com.leixing.递归迭代.DFS;

import java.util.HashMap;
import java.util.Map;

/**
 * 记忆化搜索
 * 在考虑加入「记忆化」时，我们只需要将 DFS 方法签名中的【可变】参数作为维度，DFS 方法中的返回值作为存储值即可。
 * 通常我们会使用「数组」来作为我们缓存中间结果的容器，
 * 对应到本题，就是需要一个 boolean[石子列表下标][跳跃步数] 这样的数组，但使用布尔数组作为记忆化容器往往无法区分「状态尚未计算」和「状态已经计算，并且结果为 false」两种情况。
 * 因此我们需要转为使用 int[石子列表下标][跳跃步数]，默认值 00 代表状态尚未计算，-1−1 代表计算状态为 false，11 代表计算状态为 true。
 * 接下来需要估算数组的容量，可以从「数据范围」入手分析。
 * 根据 2 <= stones.length <= 2000，我们可以确定第一维（数组下标）的长度为 20092009，而另外一维（跳跃步数）是与跳转过程相关的，无法直接确定一个精确边界，但是一个显而易见的事实是，跳到最后一块石子之后的位置是没有意义的，因此我们不会有「跳跃步长」大于「石子列表长度」的情况，因此也可以定为 20092009（这里是利用了由下标为 ii 的位置发起的跳跃不会超过 i + 1i+1 的性质）。
 * 至此，我们定下来了记忆化容器为 int[][] cache = new int[2009][2009]。
 * 但是可以看出，上述确定容器大小的过程还是需要一点点分析 & 经验的。
 * 那么是否有思维难度再低点的方法呢？
 * 答案是有的，直接使用「哈希表」作为记忆化容器。「哈希表」本身属于非定长容器集合，我们不需要分析两个维度的上限到底是多少。
 */
public class 青蛙過河2 {

        Map<Integer, Integer> map = new HashMap<>();
        // int[][] cache = new int[2009][2009];
        Map<String, Boolean> cache = new HashMap<>();
        public boolean canCross(int[] ss) {
            int n = ss.length;
            for (int i = 0; i < n; i++) {
                map.put(ss[i], i);
            }
            // check first step
            if (!map.containsKey(1)) return false;
            return dfs(ss, ss.length, 1, 1);
        }
        boolean dfs(int[] ss, int n, int u, int k) {
            String key = u + "_" + k;
            // if (cache[u][k] != 0) return cache[u][k] == 1;
            if (cache.containsKey(key)) return cache.get(key);
            if (u == n - 1) return true;
            for (int i = -1; i <= 1; i++) {
                if (k + i == 0) continue;
                int next = ss[u] + k + i;
                if (map.containsKey(next)) {
                    boolean cur = dfs(ss, n, map.get(next), k + i);
                    // cache[u][k] = cur ? 1 : -1;
                    cache.put(key, cur);
                    if (cur) return true;
                }
            }
            // cache[u][k] = -1;
            cache.put(key, false);
            return false;
        }
}
//时间复杂度：O(n^2)
//空间复杂度：O(n^2)
