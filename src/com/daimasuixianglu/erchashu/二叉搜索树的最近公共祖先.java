package com.daimasuixianglu.erchashu;

/**
 ** 思路
 *  做过二叉树：公共祖先问题题目的同学应该知道，利用回溯从底向上搜索，遇到一个节点的左子树里有p，右子树里有q，那么当前节点就是最近公共祖先。
 *  那么本题是二叉搜索树，二叉搜索树是有序的，那得好好利用一下这个特点。
 *  在有序树里，如果判断一个节点的左子树里有p，右子树里有q呢？
 *  其实只要从上到下遍历的时候，cur节点是数值在[p, q]区间中则说明该节点cur就是最近公共祖先了。理解这一点，本题就很好解了。
 *  和二叉树：公共祖先问题不同，普通二叉树求最近公共祖先需要使用回溯，从底向上来查找，二叉搜索树就不用了，因为搜索树有序（相当于自带方向），那么只要从上向下遍历就可以了。
 *  那么我们可以采用前序遍历（其实这里没有中节点的处理逻辑，遍历顺序无所谓了）。
 */
public class 二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }
}
