package com.daimasuixianglu.erchashu;

import java.util.Stack;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 思路
 * 一看到累加树，相信很多小伙伴都会疑惑：如何累加？遇到一个节点，然后在遍历其他节点累加？怎么一想这么麻烦呢。
 * 然后再发现这是一颗二叉搜索树，二叉搜索树啊，这是有序的啊。
 * 那么有序的元素如果求累加呢？
 * 其实这就是一棵树，大家可能看起来有点别扭，换一个角度来看，这就是一个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]，是不是感觉这就简单了。
 * 为什么变成数组就是感觉简单了呢？
 * 因为数组大家都知道怎么遍历啊，从后向前，挨个累加就完事了，这换成了二叉搜索树，看起来就别扭了一些是不是。
 * 那么知道如何遍历这个二叉树，也就迎刃而解了，从树中可以看出累加的顺序是右中左，所以我们需要反中序遍历这个二叉树，然后顺序累加就可以了。
 */
public class 把二叉搜索树转换为累加树 {
    int sum;
    public TreeNode convertBST(TreeNode root) {
        sum=0;
        convertBST1(root);
        return root;
    }
    public void  convertBST1(TreeNode root){
        if (root==null){
            return;
        }
        // 按右中左顺序遍历，累加即可
        convertBST1(root.right);
        sum+=root.val;
        root.val=sum;
        convertBST1(root.left);
    }
}
class Solution11{
    int pre;
    Stack<TreeNode> stack=new Stack<>();
    public TreeNode convertBST(TreeNode root) {
        pre=0;
        traversal(root);
        return root;
    }
    private void traversal(TreeNode root){
        TreeNode cur=root;
        while (cur!=null || !stack.isEmpty()){
            if (cur!=null){
                stack.push(cur);
                cur=cur.right;
            }
            else {
                cur=stack.pop();
                cur.val+=pre;
                pre=cur.val;
                cur=cur.left;
            }
        }
    }
}
