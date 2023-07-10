package com.leixing.树的搜索;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
 * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
 * 如果第二小的值不存在的话，输出 -1 。
 *
 * 树的遍历
 * 一个朴素的做法是，直接对树进行遍历（广度 & 深度），使用 HashSet 进行存储，
 * 得到所有去重后的节点大小。
 * 然后找次小值的方式有多种：可以通过排序找次小值，复杂度为 O(nlogn)；
 * 也可以使用经典的两个变量 & 一次遍历的方式，找到次小值，复杂度为 O(n)。
 */
public class 二叉树中第二小的节点 {
    Set<Integer> set=new HashSet<>();
    public int findSecondMinimumValue (TreeNode root) {

        dfs(root);
        if (set.size() < 2) return -1;
        int first=Integer.MAX_VALUE,second=Integer.MAX_VALUE;
        for (int i : set) {
            if (i <= first){
                second=first;
                first=i;
            }else if (i <= second){
                second=i;
            }
        }
        return second;
    }
    void dfs(TreeNode root){
        if (root == null) return;
        set.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
/**
 * 时间复杂度：树的搜索复杂度为 O(n)，通过线性遍历找次小值，复杂度为 O(n)。整体复杂度为 O(n)
 * 空间复杂度：O(n)
 */
