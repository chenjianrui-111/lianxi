package com.lizi;

import java.util.*;

public class 小红的二叉树构造 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]=i+1;
        }
        sortedArrayToBST(arr);

    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root=traversal(nums,0,nums.length-1);
        return root;
    }
    public static TreeNode traversal(int[] nums,int left,int right){
        if (left > right) return null;
        int mid=left+(right-left)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=traversal(nums,left,mid-1);
        root.right=traversal(nums,mid+1,right);
        return root;
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            int currentSize=queue.size();
            for (int i = 1; i <=currentSize ; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        Collections.reverse(res);
        return res;
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int val){this.val=val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
}
