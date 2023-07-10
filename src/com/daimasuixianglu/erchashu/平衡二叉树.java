package com.daimasuixianglu.erchashu;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 */
public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    private int getHeight(TreeNode root){
        if (root==null){
            return 0;
        }
        int leftHeight=getHeight(root.left);
        if (leftHeight == -1){
            return -1;
        }
        int rightHeight=getHeight(root.right);
        if (rightHeight == -1){
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
class Solution02 {
/**
 * 迭代法，效率较低，计算高度时会重复遍历
 * 时间复杂度：O(n^2)
 */
       public boolean isBalanced(TreeNode root) {
           if (root == null){
               return true;
           }
           Stack<TreeNode> stack=new Stack<>();
           TreeNode pre=null;
           while (root !=null || !stack.isEmpty()){
               while (root !=null){
                   stack.push(root);
                   root=root.left;
               }
               TreeNode inNode=stack.peek();
               // 右结点为null或已经遍历过
               if (inNode.right == null || inNode.right == pre) {
                   // 比较左右子树的高度差，输出
                   if (Math.abs(getHeight(inNode.left) - getHeight(inNode.right)) > 1) {
                       return false;
                   }
                   stack.pop();
                   pre = inNode;
                   root = null;// 当前结点下，没有要遍历的结点了
               } else {
                   root = inNode.right;// 右结点还没遍历，遍历右结点
               }
           }
           return true;
      }

    /**
     * 层序遍历，求结点的高度
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
}
