package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.HashMap;

/**
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 */
public class 从中序和后序遍历序列构造二叉树 {
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i <inorder.length ; i++) {
            hashMap.put(inorder[i],i);
        }
       return build(inorder,0,inorder.length - 1,postorder,0,postorder.length - 1);
    }
    TreeNode build(int[] inorder,int inStart ,int inEnd,int[] postorder,int postStart,int postEnd){
        if (postStart > postEnd){
            return null;
        }
        int rootVal = postorder[postEnd];
        int index = hashMap.get(rootVal);
        int leftSize = index - inStart;
        TreeNode root =new TreeNode(rootVal);
        root.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder,index + 1,inEnd,postorder,postStart + leftSize ,postEnd - 1);
        return root;
    }
}
