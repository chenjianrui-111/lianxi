package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 */
public class 路径总和二 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root==null) return res;
        path.add(root.val);// 把根节点放进路径
        traversal(root,targetSum-root.val);
        return  res;
    }
    public void  traversal(TreeNode root,int count){
        if (root.left==null && root.right==null && count==0){// 遇到了叶子节点且找到了和为targetSum的路径
            res.add(new ArrayList<>(path));
            return;
        }
        if (root.left==null && root.right==null) return; // 遇到叶子节点而没有找到合适的边，直接返回
        if (root.left!=null){// 左 （空节点不遍历）
            path.add(root.left.val);
            count-=root.left.val;
            traversal(root.left,count);// 递归
            count+=root.left.val;// 回溯
            path.removeLast();// 回溯
        }
        if (root.right!=null){// 右 （空节点不遍历）
            path.add(root.right.val);
            count-=root.right.val;
            traversal(root.right,count); // 递归
            count+=root.right.val;// 回溯
            path.removeLast();// 回溯
        }
        return;
    }
}
