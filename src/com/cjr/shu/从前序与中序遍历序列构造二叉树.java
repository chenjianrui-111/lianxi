package com.cjr.shu;

import java.util.HashMap;
import java.util.Map;

public class 从前序与中序遍历序列构造二叉树 {
    Map<Integer,Integer> map=new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int num=preorder.length;
        for (int i=0;i<num;i++){
            //用哈希表的原因：避免每一次找根节点都要遍历一次数组
            map.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,num-1,0,num-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preleft,int preright,int inleft,int inright) {
        //递归终止条件
        if (preleft>preright){
            return null;
        }
        TreeNode root=new TreeNode(preorder[preleft]);
        //找到根节点在中序遍历中的位置（下标），从而确定左子树和右子树的长度
        int rootIndex=map.get(preorder[preleft]);
        //确定左右子树在前序、中序遍历中的区间
        root.left=myBuildTree(preorder,inorder,preleft+1,rootIndex-inleft+preleft,inleft,rootIndex-1);
        root.right=myBuildTree(preorder,inorder,rootIndex-inleft+preleft+1,preright,rootIndex+1,inright);
        return root;
    }
}
