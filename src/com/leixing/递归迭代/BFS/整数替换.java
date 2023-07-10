package com.leixing.递归迭代.BFS;

import java.util.HashMap;
import java.util.Map;

/**
 *给定一个正整数 n ，你可以做如下操作：
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * 返回 n 变为 1 所需的 最小替换次数 。
 *提示：
 * 1 <= n <= 2^31 - 1
 * DFS
 * 运用 DFS 进行求解。为防止重复处理某些数值，可以使用「哈希表」进行记忆化
 */
public class 整数替换 {
    Map<Long,Integer> map=new HashMap<>();
    public int integerReplacement(int n) {
        return dfs(n * 1L);
    }
    int dfs(Long n){
        if (n == 1) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int ans= n % 2 == 0 ? dfs(n / 2) : Math.min(dfs((n + 1) ),dfs(n - 1) );
        map.put(n,++ans);
        return ans;
    }
}
//时间复杂度：O(logn)
//空间复杂度：O(logn)
