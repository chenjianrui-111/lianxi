package com.leixing.树的搜索;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 基本思路
 这道题本质上考的是「将迭代版的中序遍历代码」做等价拆分。
 我们知道，中序遍历的基本逻辑是：处理左子树 -> 处理当前节点 -> 处理右子树。
 其中迭代做法是利用「栈」进行处理：
 1.先将当前节点的所有左子树压入栈，压到没有为止
 2.将最后一个压入的节点弹出（栈顶元素），加入答案
 3.将当前弹出的节点作为当前节点，重复步骤一
 相应的裸题在这里：94. 二叉树的中序遍历
 */
class BSTIterator {
    Deque<TreeNode> d = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {

        // 步骤 1
        dfsLeft(root);
    }

    public int next() {

        // 步骤 2
        TreeNode root = d.pollLast();
        int ans = root.val;
        // 步骤 3
        root = root.right;
        // 步骤 1
        dfsLeft(root);
        return ans;
    }

    public boolean hasNext() {

        return !d.isEmpty();
    }

    void dfsLeft(TreeNode root) {
        while (root != null) {
            d.addLast(root);
            root = root.left;
        }
    }
}

/**
 * 时间复杂度：由于每个元素都是严格「进栈」和「出栈」一次，复杂度为均摊 O(1)
 * 空间复杂度：栈内最多保存与深度一致的节点数量，复杂度为 O(h)
 */
 class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
    }
}
