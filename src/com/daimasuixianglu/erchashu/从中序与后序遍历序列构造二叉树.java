package com.daimasuixianglu.erchashu;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 如何确定子树的左右边界？
 * 根据二叉树的性质，我们可以依次采取下述步骤：
 * 1、先利用后序遍历找根节点：后序遍历的最后一个数，就是根节点的值；
 * 2、在中序遍历中找到根节点的位置 k，则 k 左边是左子树的中序遍历，右边是右子树的中序遍历;
 * 3、假设il,ir对应子树中序遍历区间的左右端点， pl,pr对应子树后序遍历区间的左右端点。那么左子树的中序遍历的区间为 [il, k - 1]，右子树的中序遍历的区间为[k + 1, ir];
 * 4、由步骤3可知左子树中序遍历的长度为k - 1 - il + 1，由于一棵树的中序遍历和后序遍历的长度相等，因此后序遍历的长度也为k - 1 - il + 1。
 * 这样，根据后序遍历的长度，我们可以推导出左子树后序遍历的区间为[pl, pl + k - 1 - il]，右子树的后序遍历的区间为[pl + k - 1 - il + 1, pr - 1];
 * 如何在中序遍历中对根节点快速定位？
 * 一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。
 * 我们可以考虑使用哈希表来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，
 * 键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。这样在中序遍历中查找根节点位置的操作，
 * 只需要 O(1) 的时间。
 */
public class 从中序与后序遍历序列构造二叉树 {
    private Map<Integer,Integer> pos = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i = 0; i < n; i++)
            pos.put(inorder[i], i);  //记录中序遍历的根节点位置
        return dfs( inorder, postorder, 0, n - 1, 0, n - 1);
    }
    public TreeNode dfs(int[] inorder, int[] postorder, int il, int ir,int pl, int pr)
    {
        if(pl > pr ) return null;
        int k = pos.get(postorder[pr]); //中序遍历根节点位置
        TreeNode root = new TreeNode(postorder[pr]); //创建根节点
        root.left  = dfs(inorder, postorder, il, k - 1, pl, pl + k - 1 - il);
        root.right = dfs(inorder, postorder, k + 1, ir, pl + k - 1 - il + 1, pr - 1);
        return root;
    }
}
