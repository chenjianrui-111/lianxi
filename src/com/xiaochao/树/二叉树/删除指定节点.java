package com.xiaochao.树.二叉树;

/**
 * 树结构中的删除操作
 *如果要删除的节点是叶子节点，那么删除它即可。
 * 如果要删除的节点有子节点，就删除整颗子树。
 * 删除操作的实现思路
 * 在判断时，因为树是单向的，所以类似于链表。
 * 应该判断，当前节点的左节点、右节点是否是要删除的节点，而不是判断本节点。
 * 思路是：
 * 先判断要删除的是否是根节点，如果是，直接将二叉树清空。返回
 * 要删除的不是根节点
 * 判断本节点的左节点是否存在。如果存在，且就是要删除的节点，就让它的左指针域置null；返回
 * 判断本节点的右节点是否存在。如果存在，且就是要删除的节点，就让它的右指针域置null；返回
 * 如果进行到了这一步，说明这个节点的左、右节点均不是要删除的节点，就向左子树进行递归删除；
 * 如果进行到了这一步，就继续向右子树递归删除
 * 如果进行到了这一步，说明要删除的节点不存在。
 */
public class 删除指定节点 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        // 删除指定节点
        public void delete(TreeNode node) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) { // 要删除的节点是叶子节点
                if (node.parent != null) { // 如果要删除的节点有父节点
                    if (node.parent.left == node) {
                        node.parent.left = null; // 将父节点的左子节点置为空
                    } else {
                        node.parent.right = null; // 将父节点的右子节点置为空
                    }
                }
                node.parent = null; // 将当前节点的父节点指针也置为空
            } else if (node.left == null) { // 要删除的节点只有右子节点
                if (node.parent != null) { // 如果要删除的节点有父节点
                    if (node.parent.left == node) {
                        node.parent.left = node.right; // 将父节点的左子节点指向当前节点的右子节点
                    } else {
                        node.parent.right = node.right; // 将父节点的右子节点指向当前节点的右子节点
                    }
                }
                node.right.parent = node.parent; // 将右子节点的父节点指针指向当前节点的父节点
            } else if (node.right == null) { // 要删除的节点只有左子节点
                if (node.parent != null) { // 如果要删除的节点有父节点
                    if (node.parent.left == node) {
                        node.parent.left = node.left; // 将父节点的左子节点指向当前节点的左子节点
                    } else {
                        node.parent.right = node.left; // 将父节点的右子节点指向当前节点的左子节点
                    }
                }
                node.left.parent = node.parent; // 将左子节点的父节点指针指向当前节点的父节点
            } else { // 要删除的节点有左右子节点
                TreeNode successor = getSuccessor(node); // 找到后继节点
                node.val = successor.val; // 将后继节点的值赋值给当前要删除的节点
                delete(successor); // 删除后继节点
            }
        }

        // 找到指定节点的后继节点（中序遍历中的后一个节点）
        public TreeNode getSuccessor(TreeNode node) {
            if (node.right != null) {
                // 如果当前节点有右子树，则后继节点是该右子树的最左下角节点
                TreeNode p = node.right;
                while (p.left != null) {
                    p = p.left;
                }
                return p;
            } else {
                // 如果当前节点没有右子树，则需要向上查找第一个左拐点，即为后继节点
                TreeNode p = node.parent;
                TreeNode q = node;
                while (p != null && q == p.right) {
                    q = p;
                    p = p.parent;
                }
                return p;
            }
        }
    }
}
