package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

public class 在BST中删除一个数 {
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 这两个 if 把情况 1 和 2 都正确处理了
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 处理情况 3
            // 获得右⼦树最⼩的节点
            TreeNode minNode = getMin(root.right);
            // 删除右⼦树最⼩的节点
            root.right = deleteNode(root.right, minNode.val);
            // ⽤右⼦树最⼩的节点替换 root 节点
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最⼩的
        while (node.left != null) node = node.left;
        return node;
    }
}
