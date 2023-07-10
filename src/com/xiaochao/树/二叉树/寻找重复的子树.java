package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树 root，返回所有重复的子树。
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 */
public class 寻找重复的子树 {
    //记录所有子树以及出现的次数
    HashMap<String,Integer> subTrees = new HashMap<>();
    //记录重复的子树根节点
    LinkedList<TreeNode> res =new LinkedList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return res;
    }
    String serialize(TreeNode root){
        if (root == null){
            return "#";
        }
        //先算左右子树的序列化结果
        String left = serialize(root.left);
        String right= serialize(root.right);

        String myself = left + "," + right +"," +root.val;

        int freq = subTrees.getOrDefault(myself,0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1){
            res.add(root);
        }
        subTrees.put(myself,freq+1);
        return myself;
    }
}
