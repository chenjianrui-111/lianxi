package com.cjr.shu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class erChaShuJuChiCengBianLi {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if (root ==null){
            return res;
        }
        //创建队列，保存节点
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        boolean leftToRight=true;//第一步先从左打印
        while (!queue.isEmpty()){
            //记录每层节点的值
            List<Integer> level=new ArrayList<>();
            //统计这一层有多少节点
            int count=level.size();
            //遍历这一层的所有节点，把他们全部从队列中移出来，顺便
            //把他们的值加入到集合level中，接着再把他们的子节点（如果有）
            //加入到队列中

           for (int i=0;i<count;i++){
               TreeNode node=queue.poll();
               if (leftToRight){
                   level.add(node.val);
               }else {
                   level.add(0,node.val);
               }
               //左右子节点如果不为空会被加入到队列中
               if (node.left != null)
                   queue.add(node.left);
               if (node.right != null)
                   queue.add(node.right);
           }
         //把这一层节点的值加入到集合res中
            res.add(level);
           //改变下一次访问的方向
            leftToRight=!leftToRight;
        }
        return res;
    }
}
