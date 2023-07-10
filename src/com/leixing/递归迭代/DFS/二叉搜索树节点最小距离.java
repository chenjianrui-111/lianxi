package 树的搜索;


import java.util.*;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 朴素解法（BFS & DFS）
 * 如果不考虑利用二叉搜索树特性的话，一个朴素的做法是将所有节点的 val 存到一个数组中。
 * 对数组进行排序，并获取答案。
 * 将所有节点的 val 存入数组，可以使用 BFS 或者 DFS。
 */
public class 二叉搜索树节点最小距离 {
    public int minDiffInBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        //BFS
        Deque<TreeNode> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            TreeNode poll =deque.pollFirst();
            list.add(poll.val);
            if (poll.left !=null) deque.addLast(poll.left);
            if (poll.right !=null) deque.addLast(poll.right);
        }

        Collections.sort(list);
        int n=list.size();
        int ans=Integer.MAX_VALUE;
        for (int i=1;i < n;i++){
            int cur=Math.abs(list.get(i) - list.get(i-1));
            ans=Math.min(ans,cur);
        }
        return ans;
    }
}
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
    }
 }
 /**
  *时间复杂度：O(nlogn)
  * 空间复杂度：O(n)
  */