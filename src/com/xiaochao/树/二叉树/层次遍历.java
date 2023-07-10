package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 层次遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        //必须加上
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        //初始时，将根节点加入队列
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            //必须缓存当前一层的长度，因为处理时长度在变化
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //从队列中顺序取出要处理的节点
                TreeNode node = queue.poll();
                list.add(node.val);
                //如果有左、右节点，就加入队列中
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }
}
