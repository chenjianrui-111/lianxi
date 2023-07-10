package com.daimasuixianglu.erchashu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案
 *在调用traversal的时候为什么传入的left和right为什么是0和nums.size() - 1，
 * 因为定义的区间为左闭右闭
 */
public class 将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root=traversal(nums,0,nums.length-1);
        return root;
    }
    public TreeNode traversal(int[] nums,int left,int right){
        if (left > right) return null;
        int mid=left+(right-left)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=traversal(nums,left,mid-1);
        root.right=traversal(nums,mid+1,right);
        return root;
    }
}
//迭代法
class  Soulution10{
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        //根节点初始化
        TreeNode root = new TreeNode(-1);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> leftQueue = new LinkedList<>();
        Queue<Integer> rightQueue = new LinkedList<>();

        //根节点入队列
        nodeQueue.offer(root);
        //0在左区间下表初始位置
        leftQueue.offer(0);
        //nums.length-1为右区间下表初始位置
        rightQueue.offer(nums.length - 1);

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            int mid = left + (right - left) / 2;

            //将mid对应的元素给中间节点
            curNode.val = nums[mid];
            // 处理左区间
            if (left <= mid - 1) {
                curNode.left = new TreeNode(-1);
                nodeQueue.offer(curNode.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }

            // 处理右区间
            if (right >= mid + 1) {
                curNode.right = new TreeNode(-1);
                nodeQueue.offer(curNode.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }
        }
        return root;
    }
}
