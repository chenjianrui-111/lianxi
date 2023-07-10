package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.HashMap;

/**
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 */
public class 从前序和后序遍历序列构造二叉树 {
    HashMap<Integer,Integer> hashMap =new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i <postorder.length; i++) {
            hashMap.put(postorder[i],i);
        }
        return build(preorder,0,preorder.length - 1,postorder,0,postorder.length - 1);
    }
    TreeNode build(int[] preorder,int preStart ,int preEnd,int[] postorder,int postStart,int postEnd){
        if (preStart > preEnd){
            return null;
        }
        if (preStart == preEnd){
            return new TreeNode(preorder[preStart]);
        }

        int rootVal = preorder[preStart];
        // root.left 的值是前序遍历第⼆个元素
        // 通过前序和后序遍历构造⼆叉树的关键在于通过左⼦树的根节点
        // 确定 preorder 和 postorder 中左右⼦树的元素区间
        int leftRootVal = preorder[preStart + 1];
        // leftRootVal 在后序遍历数组中的索引
        int index = hashMap.get(leftRootVal);
        // 左⼦树的元素个数
        int leftSize = index - postStart + 1;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右⼦树
        // 根据左⼦树的根节点索引和元素个数推导左右⼦树的索引边界
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);
        return root;
    }
}
