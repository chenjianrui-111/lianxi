package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.HashMap;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点
 *输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 */
public class 从前序和中序遍历序列构造二叉树 {
    HashMap<Integer,Integer> hashMap =new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i <inorder.length ; i++) {
            hashMap.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd){

        if (preStart > preEnd){
            return null;
        }
        //root节点的值
        int rootVal = preorder[preStart];
        //root节点在inorder中的索引下标
        int index = hashMap.get(rootVal);

        int leftSize = index - inStart;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1,preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }
}
