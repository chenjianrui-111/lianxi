package com.xiaochao.笔试;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Demo12 {
    static  class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    static class BinaryTreeTraversal{

        public List<List<Integer>> levelOrder(TreeNode root){
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                List<Integer> levelList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    levelList.add(node.val);
                    if (node.left != null){
                        queue.offer(node.left);
                    }
                    if (node.right != null){
                        queue.offer(node.right);
                    }
                }
                res.add(levelList);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(6);
        node.right = new TreeNode(12);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(4);
        BinaryTreeTraversal bt = new BinaryTreeTraversal();
        List<List<Integer>> res = bt.levelOrder(node);
        System.out.println(res);
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}
class Solution123{


    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        helper(result,root,0);
        return result;
    }
    public void helper(List<List<Integer>> result,TreeNode root,int level){
        if (root == null ) return;
        if (result.size() <= level){
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        helper(result,root.left,level + 1);
        helper(result, root.right, level + 1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(6);
        node.right = new TreeNode(12);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(4);
        Solution123 so = new Solution123();
        List<List<Integer>> lists = so.levelOrder(node);
        System.out.println(lists);
    }
}
