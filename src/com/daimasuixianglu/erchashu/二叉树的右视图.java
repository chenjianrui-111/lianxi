package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧所能看到的节点值
 *
 * 层序遍历的时候，判断是否遍历到单层的最后面的元素，如果是，就放进result数组中，
 * 随后返回result就可以了。
 */
public class 二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if (root == null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            int currentLevelSize=queue.size();
            for (int i = 1; i <=currentLevelSize ; i++) {
                TreeNode node=queue.poll();
                level.add(node.val);
                if (i==currentLevelSize) res.add(node.val);
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}
