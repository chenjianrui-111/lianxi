package com.daimasuixianglu.erchashu;

import com.cjr.shu.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */
public class 在每个树行中找最大值 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();

        if (root == null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            int maxValue=Integer.MIN_VALUE;
            for (int i = 0; i <size ; i++) {
                TreeNode node=queue.poll();
                maxValue=node.val>maxValue ?node.val: maxValue;
                if (node.left !=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(maxValue);
        }
        return res;
    }
}
