package com.xiaochao.树.二叉树;

import com.cjr.shu.Node;

public class 填充每个节点的下一个右侧节点指针 {
    // 主函数
    Node connect(Node root) {
        if (root == null) return null;
        // 遍历「三叉树」，连接相邻节点
        traverse(root.left, root.right);
        return root;
    }
    // 三叉树遍历框架
    void traverse(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序位置 ****/
        // 将传⼊的两个节点穿起来
        node1.next = node2;

        // 连接相同⽗节点的两个⼦节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越⽗节点的两个⼦节点
        traverse(node1.right, node2.left);
    }
}
