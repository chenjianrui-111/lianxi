package com.leixing.递归迭代.DFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 *
 * BFS
 * 考察「层序遍历」，在遍历过程中需要记录层下标，并根据层下标检查进行「节点值的奇偶性」和「是否满足递增/递减」。
 * 额外使用一个布尔变量 flag 记录层序是否为偶数（判断节点值的奇偶性），
 * 使用 prev 记录当前层的上一节点的值（判断是否满足递增/递减）即可，prev 起始值可根据数据范围设置
 * 为哨兵值。
 */
public class 奇偶树 {
    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> d=new ArrayDeque<>();
        boolean flag=true;
        d.addLast(root);
        while (!d.isEmpty()){
            int size=d.size(),prev=flag ? 0 : 0x3f3f3f3f;
            while (size-- > 0){
                    TreeNode node =d.pollFirst();
                    int cur=node.val;
                    if (flag && (cur % 2 == 0 || cur <= prev)) return false;
                    if (!flag && (cur % 2 != 0 || cur >=prev)) return false;
                    prev = cur;
                    if (node.left != null) d.addLast(node.left);
                    if (node.right != null) d.addLast(node.right);
            }
            flag=!flag;
        }
        return true;
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
