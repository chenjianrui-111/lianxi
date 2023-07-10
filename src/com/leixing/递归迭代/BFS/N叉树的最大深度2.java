package com.leixing.递归迭代.BFS;

/**
 * DFS
 * 根据题意，可以写出如下的 DFS 实现：从 root 的所有子节点中的取最大深度，
 * 并在此基础上加一（统计 root 节点）即是答案。
 */
public class N叉树的最大深度2 {
    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
        }
        int ans=0;
        for (Node node:root.children){
            ans=Math.max(ans,maxDepth(node));
        }
        return ans+1;
    }
}
