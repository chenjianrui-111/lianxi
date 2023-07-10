package com.daimasuixianglu.erchashu;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 */
public class 删除二叉搜索树中的节点 {
     public TreeNode deleteNode(TreeNode root, int key) {
         root=delete(root,key);
         return root;
    }
    private TreeNode delete(TreeNode root,int key){
         if (root==null) return null;// 第一种情况：没找到删除的节点，遍历到空节点直接返回了
         if (root.val>key){
             root.left=deleteNode(root.left,key);
         }else if (root.val<key){
             root.right=deleteNode(root.right,key);
         }else {
             if (root.left==null) return root.right;// 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
             if (root.right==null) return root.left; // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
             // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
             // 并返回删除节点右孩子为新的根节点。
             TreeNode tmp=root.right;// 找右子树最左面的节点,把root节点保存一下，下面来删除
             while (tmp.left!=null){
                 tmp=tmp.left;
             }
             root.val=tmp.val;//返回旧root的右孩子作为新root
             root.right=delete(root.right,tmp.val);
         }
         return root;
    }
}
