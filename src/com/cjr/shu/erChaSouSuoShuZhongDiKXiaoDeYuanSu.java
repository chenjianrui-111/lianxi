package com.cjr.shu;

import java.util.ArrayDeque;
import java.util.Deque;

public class erChaSouSuoShuZhongDiKXiaoDeYuanSu {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> deque=new ArrayDeque<>();
        while (root !=null || !deque.isEmpty()){
            while (root !=null){
                deque.push(root);
                root=root.left;
            }
            root=deque.pop();
            --k;
            if (k==0){
                break;
            }
            root=root.right;
        }
        return root.val;
    }
}
