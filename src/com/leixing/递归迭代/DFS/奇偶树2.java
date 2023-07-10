package com.leixing.递归迭代.DFS;

import java.util.HashMap;
import java.util.Map;

/**DFS
 同样的思路，自然也能够使用 DFS 进行求解。
 由于 DFS 是深度优先，因此我们在 DFS 过程中除了要记录当前层编号（判断节点值的奇偶性），
 还要记录每层最后上一次遍历到的节点值为多少（判断是否满足递增/递减）。
 *
 */
public class 奇偶树2 {
    Map<Integer, Integer> map = new HashMap<>();
    public boolean isEvenOddTree(TreeNode root) {
        return dfs(root, 0);
    }
    boolean dfs(TreeNode root, int idx) {
        boolean flag = idx % 2 == 0;
        int prev = map.getOrDefault(idx, flag ? 0 : 0x3f3f3f3f), cur = root.val;
        if (flag && (cur % 2 == 0 || cur <= prev)) {
            return false;
        }
        if (!flag && (cur % 2 != 0 || cur >= prev)) {
            return false;
        }
        map.put(idx, root.val);
        if (root.left != null && !dfs(root.left, idx + 1)) {
            return false;
        }
        if (root.right != null && !dfs(root.right, idx + 1)) {
            return false;
        }
        return true;
    }
}
/**
 *时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
