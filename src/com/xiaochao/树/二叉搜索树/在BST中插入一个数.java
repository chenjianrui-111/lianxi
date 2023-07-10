package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

public class 在BST中插入一个数 {
    TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插⼊新节点
        if (root == null) return new TreeNode(val);
        // if (root.val == val)
        // BST 中⼀般不会插⼊已存在元素
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}
