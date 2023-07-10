package com.daimasuixianglu.erchashu;

import com.cjr.shu.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */
public class N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res=new ArrayList<>();
        Queue<Node> queue=new LinkedList<>();

        if (root == null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentSize=queue.size();
            List<Integer> level=new LinkedList<>();
            for (int i = 1; i <=currentSize ; i++) {
                Node node=queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            res.add(level);
        }
        return res;
    }
}
