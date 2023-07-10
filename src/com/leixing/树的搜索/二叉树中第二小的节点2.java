package com.leixing.树的搜索;

/**
 *递归
 * 解法一显然没有利用到本题核心条件 :「root.val = min(root.left.val, root.right.val)」和「每个子节点数量要么是 0 要么是 2」。
 * 我们可以设计如下递归函数，含义为 从 root 为根的树进行搜索，找到值比 cur 大的最小数。然后使用全局变量 ans 存储答案。
 * void dfs(TreeNode root, int cur)
 * 那么最终搜索范围为 dfs(root, root.val)，这是因为 性质 root.val = min(root.left.val, root.right.val)，即最小值会不断往上传递，最终根节点必然是全局最小值。
 * 然后再结合「每个子节点数量要么是 0 要么是 2」，我们可以特判一下 ans 是否为第一次赋值，如果给 ans 赋了新值或者更新了更小的 ans，则不再需要往下搜索了。
 */
public class 二叉树中第二小的节点2 {

    int ans = -1;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return ans;
    }
    void dfs(TreeNode root, int cur) {
        if (root == null) return ;
        if (root.val != cur) {
            if (ans == -1) ans = root.val;
            else ans = Math.min(ans, root.val);
            return ;
        }
        dfs(root.left, cur);
        dfs(root.right, cur);
    }

}
/**
 *时间复杂度：O(n)
 * 空间复杂度：忽略递归带来的空间开销。复杂度为 O(1)
 */
