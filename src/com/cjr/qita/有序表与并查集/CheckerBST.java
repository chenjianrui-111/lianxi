package com.cjr.qita.有序表与并查集;

//现给定树的根结点指针TreeNode* root，编辑函数返回一个bool值，判断该树是否为二叉查找树。
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}*/

public class CheckerBST {
   public boolean CheckerBST(TreeNode root){
       if (root == null) {
           return true;
       }
       int val=root.val;
       if (root.left !=null){
           TreeNode left=root.left;
           while (left.right !=null){
               left=left.right;
           }
           if (left.val > val){
               return false;
           }
       }
       if (root.right != null){
           TreeNode right=root.right;
           while(right.left!=null){
               right=right.left;
           }
           if(right.val<val){
               return false;
           }
       }
       return CheckerBST(root.left) && CheckerBST(root.right);

   }
}
 class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
